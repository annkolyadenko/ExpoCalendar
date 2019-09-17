package ua.com.expo.service.serviceImpl;

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
import ua.com.expo.persistence.dao.interfaces.IExpoDao;
import ua.com.expo.persistence.dao.interfaces.IPaymentDao;
import ua.com.expo.persistence.dao.interfaces.ITicketDao;
import ua.com.expo.persistence.dao.interfaces.IUserDao;
import ua.com.expo.service.ITicketService;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.time.Instant;
import java.util.List;
import java.util.logging.Logger;

public class TicketService implements ITicketService {

    //TODO
    private static final Logger LOGGER = Logger.getLogger(TicketService.class.getName());
    private static AbstractDaoFactory factory = MySqlDaoFactory.getInstance();
    private static ModelMapper modelMapper = new ModelMapper();
    private ITicketDao ticketDao;
    private IPaymentDao paymentDao;
    private IExpoDao expoDao;
    private IUserDao userDao;
    private ILogic logic;


    @Override
    public Ticket purchaseTicket(Long userId, Long expoId, Long ticketsAmount) throws NoSuchAlgorithmException, SQLException, ClassNotFoundException, InvalidKeySpecException, IOException {
        expoDao = factory.getExpoDao();
        Expo expo = expoDao.findEntityById(expoId);
        System.out.println(expo);
        paymentDao = factory.getPaymentDao();
        logic = new LogicImpl();
        BigDecimal value = logic.totalValue(expo.getPrice(), ticketsAmount);
        Payment payment = new Payment.Builder().value(value).build();
        Long paymentId = paymentDao.createPaymentWithGeneratedKey(payment);
        //TODO
        payment.setId(paymentId);
        userDao = factory.getUserDao();
        User user = userDao.findEntityById(userId);
        ticketDao = factory.getTicketDao();
        Instant instant = Instant.now();
        Ticket ticket = new Ticket.Builder().expo(expo).user(user).payment(payment).time(instant).amount(ticketsAmount).info(TicketInfo.INFO.toString()).build();
        //TODO
        ticketDao.create(ticket);
        return ticket;
    }

    @Override
    public Long sumPurchasedTicketsByExpoId(Long expoId) throws SQLException, IOException, ClassNotFoundException {
        //TODO REWRITE TO ONE INITIALIZATION
        ticketDao = factory.getTicketDao();
        return ticketDao.sumPurchasedTicketsByExpoId(expoId);
    }

    @Override
    public List<Ticket> findAllTicketsByUserId(Long userId) throws SQLException, IOException, ClassNotFoundException {
        //TODO REWRITE TO ONE INITIALIZATION
        ticketDao = factory.getTicketDao();
        return ticketDao.findAllTicketsByUserId(userId);
    }
}
