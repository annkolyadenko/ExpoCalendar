package ua.com.expo.persistence.dao.daoImpl;

import ua.com.expo.entity.Ticket;
import ua.com.expo.persistence.connection.ConnectionPoolManager;
import ua.com.expo.persistence.dao.interfaces.ITicketDao;
import ua.com.expo.util.resource.ConfigurationManager;
import ua.com.expo.util.time.TimeConverter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MySqlTicketDao implements ITicketDao {

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
}
