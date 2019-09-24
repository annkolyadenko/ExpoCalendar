package ua.com.expo.persistence.dao.daoImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.entity.Payment;
import ua.com.expo.exception_draft.RuntimeSqlException;
import ua.com.expo.persistence.connection.ConnectionWrapper;
import ua.com.expo.persistence.dao.IPaymentDao;
import ua.com.expo.persistence.dao.mapper.Mapper;
import ua.com.expo.transaction.util.TransactionUtil;
import ua.com.expo.util.resource.ConfigurationManager;

import java.sql.*;
import java.util.Optional;

public class MySqlPaymentDao implements IPaymentDao {

    private static final Logger LOGGER = LogManager.getLogger(MySqlPaymentDao.class.getName());
    private static final TransactionUtil transactionUtil = TransactionUtil.getInstance();

    public Optional<Payment> findPaymentById(Long id) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("payment.findById");
            con = transactionUtil.getConnection();
            ps = con.createPreparedStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of((Payment) Mapper.PAYMENT.extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeSqlException(e);
        } finally {
            con.closePreparedStatement(ps);
            con.closeConnection();
        }
        return Optional.empty();
    }


    public boolean save(Payment payment) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("payment.create");
            con = transactionUtil.getConnection();
            ps = con.createPreparedStatement(sql);
            ps.setBigDecimal(1, payment.getValue());
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


    public Long savePaymentWithGeneratedKey(Payment payment) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Long primaryKey = null;
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("payment.create");
            con = transactionUtil.getConnection();
            ps = con.createPreparedStatementWithGeneratedKey(sql);
            ps.setBigDecimal(1, payment.getValue());
            ps.execute();
            rs = ps.getGeneratedKeys();
            rs.next();
            primaryKey = rs.getLong(1);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeSqlException(e);
        } finally {
            con.closePreparedStatement(ps);
            con.closeConnection();
        }
        return primaryKey;
    }
}
