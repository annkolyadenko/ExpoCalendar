package ua.com.expo.persistence.dao.daoImpl;

import ua.com.expo.entity.*;
import ua.com.expo.persistence.connection.ConnectionPoolManager;
import ua.com.expo.persistence.dao.interfaces.ITicketDao;
import ua.com.expo.util.resource.ConfigurationManager;
import ua.com.expo.util.time.TimeConverter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySqlTicketDao implements ITicketDao {

    Connection cw;
    private TimeConverter timeConverter = new TimeConverter();

    @Override
    public List<Ticket> findAll() throws SQLException {
        return null;
    }

    @Override
    public Ticket findEntityById(Long id) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean delete(Ticket entity) {
        return false;
    }

    @Override
    public boolean create(Ticket ticket) throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException {
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            /*Connection cw = ConnectionPool.getInstance().getConnection();*/
            Connection cw = null;
            try {
                cw = ConnectionPoolManager.getSimpleConnection();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(cw);
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("ticket.create");
            ps = cw.prepareStatement(sql);
            //STUB!!!!
            ps.setLong(1, ticket.getExpo().getId());
            ps.setLong(2, ticket.getUser().getId());
            ps.setLong(3, ticket.getPayment().getId());
            ps.setTimestamp(4, timeConverter.convertToDatabase(ticket.getTime()));
            ps.setLong(5, ticket.getAmount());
            //TODO HOW TO SET TEXT
            ps.setString(6, ticket.getInfo());
            ps.executeUpdate();
            flag = true;
        } finally {
            close(ps);
        }
        return flag;
    }

    @Override
    public Ticket update(Ticket entity) {
        return null;
    }


    @Override
    public Map<Long, Long> sumPurchasedTicketsByExpoId(Long expoId) {
        return null;
    }

    @Override
    public List<Ticket> findAllTicketsByUserId(Long id) throws SQLException, IOException, ClassNotFoundException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Ticket> ticketList = new ArrayList<>();
        try {
            cw = ConnectionPoolManager.getSimpleConnection();
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("ticket.findAllById");
            ps = cw.prepareStatement(sql);
            ps.setLong(1, id);
            System.out.println(ps);
            rs = ps.executeQuery();
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getLong("ticket_id"));
                Expo expo = new Expo();
                expo.setId(rs.getLong("expo_id"));
                Showroom showroom = new Showroom();
                showroom.setId(rs.getLong("showroom_id"));
                showroom.setName(rs.getString("showroom_name"));
                showroom.setInfo(rs.getString("showroom_info"));
                expo.setShowroom(showroom);
                Theme theme = new Theme();
                theme.setId(rs.getLong("theme_id"));
                theme.setName(rs.getString("theme_name"));
                expo.setTheme(theme);
                expo.setDate(timeConverter.convertToEntity(rs.getTimestamp("expo_date")));
                expo.setPrice(rs.getBigDecimal("expo_ticket_price"));
                expo.setInfo(rs.getString("expo_info"));
                ticket.setExpo(expo);
                User user = new User();
                user.setId(rs.getLong("user_id"));
                user.setRole(new Role(rs.getLong("role_id"), rs.getString("role_name")));
                user.setName(rs.getString("user_name"));
                user.setEmail(rs.getString("user_email"));
                user.setPassword(rs.getBytes("user_password"));
                user.setSalt(rs.getBytes("user_salt"));
                ticket.setUser(user);
                Payment payment = new Payment();
                payment.setId(rs.getLong("payment_id"));
                payment.setValue(rs.getBigDecimal("payment_value"));
                ticket.setPayment(payment);
                ticket.setTime(timeConverter.convertToEntity(rs.getTimestamp("ticket_date_time")));
                ticket.setAmount(rs.getLong("ticket_amount"));
                ticket.setInfo(rs.getString("ticket_info"));
                ticketList.add(ticket);
            }
            return ticketList;

        } finally {
            close(ps);
            close(rs);
        }
    }
}
