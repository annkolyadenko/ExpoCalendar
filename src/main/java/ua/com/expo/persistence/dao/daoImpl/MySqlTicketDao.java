package ua.com.expo.persistence.dao.daoImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.entity.*;
import ua.com.expo.exception_draft.RuntimeSqlException;
import ua.com.expo.persistence.connection.ConnectionWrapper;
import ua.com.expo.persistence.dao.ITicketDao;
import ua.com.expo.persistence.dao.mapper.Mapper;
import ua.com.expo.transaction_draft.TransactionUtil;
import ua.com.expo.util.resource.ConfigurationManager;
import ua.com.expo.util.time.TimeConverter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlTicketDao implements ITicketDao {

    private static final TransactionUtil transactionUtil = TransactionUtil.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(MySqlTicketDao.class.getName());
    private TimeConverter timeConverter = new TimeConverter();


    public boolean save(Ticket ticket) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("ticket.create");
            con = transactionUtil.getConnection();
            ps = con.createPreparedStatement(sql);
            ps.setLong(1, ticket.getExpo().getId());
            ps.setLong(2, ticket.getUser().getId());
            ps.setLong(3, ticket.getPayment().getId());
            ps.setTimestamp(4, timeConverter.convertToDatabase(ticket.getTime()));
            ps.setLong(5, ticket.getAmount());
            ps.setString(6, ticket.getInfo());
            ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeSqlException(e);
        } finally {
            con.closePreparedStatement(ps);
            con.closeConnection();
        }
        return flag;
    }


    @Override
    public Long sumPurchasedTicketsByExpoId(Long id) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Long result = null;
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("ticket.sumPurchasedTicketsByExpoId");
            con = transactionUtil.getConnection();
            ps = con.createPreparedStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getLong("ticket_sum");
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeSqlException(e);
        } finally {
            con.closePreparedStatement(ps);
            con.closeConnection();
        }
        return result;
    }

    @Override
    public List<Ticket> findAllTicketsByUserId(Long id) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Ticket> ticketList = new ArrayList<>();
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("ticket.findAllById");
            con = transactionUtil.getConnection();
            ps = con.createPreparedStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                ticketList.add((Ticket) Mapper.TICKET.extractFromResultSet(rs));
            }
            return ticketList;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeSqlException(e);
        } finally {
            con.closePreparedStatement(ps);
            con.closeConnection();
        }
    }
}
