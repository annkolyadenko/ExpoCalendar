package ua.com.expo.service.serviceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import ua.com.expo.entity.Expo;
import ua.com.expo.entity.Payment;
import ua.com.expo.entity.Ticket;
import ua.com.expo.entity.User;
import ua.com.expo.entity.enums.TicketInfo;
import ua.com.expo.logic.ILogic;
import ua.com.expo.logic.LogicImpl;
import ua.com.expo.persistence.dao.factory.AbstractDaoFactory;
import ua.com.expo.persistence.dao.factory.MySqlDaoFactory;
import ua.com.expo.persistence.dao.IExpoDao;
import ua.com.expo.persistence.dao.IPaymentDao;
import ua.com.expo.persistence.dao.ITicketDao;
import ua.com.expo.persistence.dao.IUserDao;
import ua.com.expo.service.ITicketService;
import ua.com.expo.service.utils.TransactionExecutable;
import ua.com.expo.service.utils.TransactionExecutor;
import ua.com.expo.transaction.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

public class TicketService implements ITicketService {

    //TODO
    private static final Logger LOGGER = LogManager.getLogger(TicketService.class.getName());
    private static AbstractDaoFactory factory = MySqlDaoFactory.getInstance();
    private static ModelMapper modelMapper = new ModelMapper();
    private final TransactionExecutor<Payment> transactionExecutor = new TransactionExecutor<>();
    private ITicketDao ticketDao;
    private IPaymentDao paymentDao;
    private IExpoDao expoDao;
    private IUserDao userDao;
    private ILogic logic;


    @Override
    @Transactional
    public boolean purchaseTicket(Long userId, Long expoId, Long ticketsAmount) {
        expoDao = factory.getExpoDao();
        paymentDao = factory.getPaymentDao();
        userDao = factory.getUserDao();
        ticketDao = factory.getTicketDao();

        Optional<Expo> expo = expoDao.findExpoById(expoId);
        Expo ex = expo.orElseThrow(() -> new RuntimeException("Can't find expo by id"));
        Optional<User> user = userDao.findUserById(userId);
        User us = user.orElseThrow(() -> new RuntimeException("Can't find user by id"));

        logic = new LogicImpl();
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

    @Override
    public Long sumPurchasedTicketsByExpoId(Long expoId) {
        //TODO REWRITE TO ONE INITIALIZATION
        ticketDao = factory.getTicketDao();
        return ticketDao.sumPurchasedTicketsByExpoId(expoId);
    }

    @Override
    public List<Ticket> findAllTicketsByUserId(Long userId) {
        //TODO REWRITE TO ONE INITIALIZATION
        ticketDao = factory.getTicketDao();
        return ticketDao.findAllTicketsByUserId(userId);
    }
}
