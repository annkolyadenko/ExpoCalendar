package ua.com.expo.service.serviceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import ua.com.expo.controller.context.Context;
import ua.com.expo.entity.Expo;
import ua.com.expo.entity.Payment;
import ua.com.expo.entity.Ticket;
import ua.com.expo.entity.User;
import ua.com.expo.entity.enums.TicketInfo;
import ua.com.expo.logic.ILogic;
import ua.com.expo.logic.LogicImpl;
import ua.com.expo.persistence.dao.*;
import ua.com.expo.persistence.dao.factory.AbstractDaoFactory;
import ua.com.expo.transaction.TransactionExecutable;
import ua.com.expo.transaction.TransactionExecutor;
import ua.com.expo.transaction.Transactional;
import ua.com.expo.util.time.IConverter;
import ua.com.expo.util.time.TimeConverter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class VisitorService {
    private static final Logger LOGGER = LogManager.getLogger(VisitorService.class.getName());
    private final IExpoDao expoDao;
    private final ITicketDao ticketDao;
    private final IPaymentDao paymentDao;
    private final IUserDao userDao;
    private final TransactionExecutor<Payment> transactionExecutor = new TransactionExecutor<>();
    private final ILogic logic = new LogicImpl();
    //TODO
    private static ModelMapper modelMapper = new ModelMapper();
    private static IConverter converter = TimeConverter.getInstance();

    public VisitorService() {
        AbstractDaoFactory daoFactory = Context.getInstance().getMySqlDaoFactory();
        this.expoDao = daoFactory.getExpoDao();
        this.ticketDao = daoFactory.getTicketDao();
        this.paymentDao = daoFactory.getPaymentDao();
        this.userDao = daoFactory.getUserDao();
    }

    public List<Expo> findAllExpoByThemeIdAndDate(Long id, Timestamp time) {
        return expoDao.findAllExpoByThemeIdAndDate(id, time);
    }

    @Transactional
    public boolean purchaseTicket(Long userId, Long expoId, Long ticketsAmount) {

        Optional<Expo> expo = expoDao.findExpoById(expoId);
        Expo ex = expo.orElseThrow(() -> new RuntimeException("Can't find expo by id"));
        Optional<User> user = userDao.findUserById(userId);
        User us = user.orElseThrow(() -> new RuntimeException("Can't find user by id"));

        BigDecimal value = logic.totalValue(ex.getPrice(), ticketsAmount);

        Instant instant = Instant.now();

        transactionExecutor.perform((new TransactionExecutable() {
            @Override
            public void execute() {
                Payment payment = new Payment.Builder().value(value).build();
                Long paymentId = paymentDao.savePaymentWithGeneratedKey(payment);
                payment.setId(paymentId);
                LOGGER.debug(payment + "Payment");
                Ticket ticket = new Ticket.Builder().expo(ex).user(us).payment(payment).time(instant).amount(ticketsAmount).info(TicketInfo.INFO.toString()).build();
                LOGGER.debug(ticket + "Ticket");
                boolean result = ticketDao.save(ticket);
                LOGGER.debug("RESULT :" + result);
            }
        }));
        return true;
    }

    public List<Ticket> findAllTicketsByUserId(Long userId) {
        return ticketDao.findAllTicketsByUserId(userId);
    }

}
