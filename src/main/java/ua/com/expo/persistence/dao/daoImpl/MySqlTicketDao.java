package ua.com.expo.persistence.dao.daoImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.entity.*;
import ua.com.expo.exception_draft.RuntimeSqlException;
import ua.com.expo.persistence.connection.ConnectionWrapper;
import ua.com.expo.persistence.dao.ITicketDao;
import ua.com.expo.persistence.dao.mapper.Mapper;
import ua.com.expo.transaction.util.TransactionUtil;
import ua.com.expo.util.resource.ConfigurationManager;
import ua.com.expo.util.time.impl.DateConverter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MySqlTicketDao implements ITicketDao {

    private static final TransactionUtil transactionUtil = TransactionUtil.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(MySqlTicketDao.class.getName());
    private DateConverter timeConverter = DateConverter.getInstance();


    public boolean save(Ticket ticket) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("ticket.create");
            con = transactionUtil.getConnection();
            LOGGER.debug("save(Ticket ticket)" + con);
            ps = con.createPreparedStatement(sql);
            ps.setLong(1, ticket.getExpo().getId());
            ps.setLong(2, ticket.getUser().getId());
            ps.setLong(3, ticket.getPayment().getId());
            ps.setTimestamp(4, timeConverter.convertToDatabase(ticket.getDate()));
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
    public Long countPurchasedTicketsByExpoId(Long id) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Long result = null;
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("ticket.sumPurchasedByExpoId");
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

    public Map<Expo, Long> countAllPurchasedTickets() {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<Expo, Long> tickets = new HashMap<>();
        try {
            con = transactionUtil.getConnection();
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("ticket.sumAllPurchased");
            ps = con.createPreparedStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                tickets.put((Expo) Mapper.EXPO.extractFromResultSet(rs), rs.getLong("ticket_sum"));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeSqlException(e);
        } finally {
            con.closePreparedStatement(ps);
            con.closeConnection();
        }
        return tickets;
    }

    @Override
    public LinkedHashMap<Expo, Long> countAllPurchasedTicketsPageable(Integer offset, Integer limit) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        LinkedHashMap<Expo, Long> tickets = new LinkedHashMap<>();
        try {
            con = transactionUtil.getConnection();
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("ticket.sumAllPurchasedPageable");
            ps = con.createPreparedStatement(sql);
            ps.setInt(1, offset);
            LOGGER.debug("OFFSET :" + offset);
            ps.setInt(2, limit);
            LOGGER.debug("LIMIT :" + limit);
            rs = ps.executeQuery();
            while (rs.next()) {
                tickets.put((Expo) Mapper.EXPO.extractFromResultSet(rs), rs.getLong("ticket_sum"));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeSqlException(e);
        } finally {
            con.closePreparedStatement(ps);
            con.closeConnection();
        }
        return tickets;
    }

    @Override
    public Integer findNumberOfRowsByUserId(Long id) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer result = null;
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("ticket.findNumberOfRowsByUserId");
            con = transactionUtil.getConnection();
            ps = con.createPreparedStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                result = rs.getInt("total");
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
    public List<Ticket> findAllTicketsByUserIdPageable(Long id, Integer offset, Integer limit) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Ticket> ticketList = new ArrayList<>();
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("ticket.findAllByUserIdPageable");
            con = transactionUtil.getConnection();
            ps = con.createPreparedStatement(sql);
            ps.setLong(1, id);
            ps.setInt(2, offset);
            LOGGER.debug("OFFSET :" + offset);
            ps.setInt(3, limit);
            LOGGER.debug("LIMIT :" + limit);
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
