package ua.com.expo.persistence.dao.daoImpl;

import ua.com.expo.entity.Payment;
import ua.com.expo.persistence.connection.ConnectionPool;
import ua.com.expo.persistence.connection.ConnectionPoolManager;
import ua.com.expo.persistence.dao.interfaces.IPaymentDao;
import ua.com.expo.util.resource.ConfigurationManager;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.List;

public class MySqlPaymentDao implements IPaymentDao {

    Connection cw;

    {
        try {
            cw = ConnectionPool.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Payment> findAll() throws SQLException {
        return null;
    }

    @Override
    public Payment findEntityById(Long id) throws SQLException, IOException, ClassNotFoundException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Payment payment = new Payment();
        try {
            /*ConnectionWrapper cw = transactionUtil.getConnection();*/
            cw = ConnectionPoolManager.getSimpleConnection();
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("payment.findById");
            ps = cw.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                payment.setId(rs.getLong("payment_id"));
                payment.setValue(rs.getBigDecimal("payment_value"));
            }
            return payment;
            /*if (rs.next()) {
                return UserMapper.getInstance().extractFromResultSet(rs);
            }*/
        } finally {
            //TODO
            close(ps);
            close(rs);
        }
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean delete(Payment entity) {
        return false;
    }

    @Override
    public boolean create(Payment payment) throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException {
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            Connection cw = null;
            try {
                cw = ConnectionPoolManager.getSimpleConnection();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(cw);
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("payment.create");
            ps = cw.prepareStatement(sql);
            //STUB!!!!
            ps.setBigDecimal(1, payment.getValue());
            ps.executeUpdate();
            flag = true;
        } finally {
            close(ps);
        }
        return flag;
    }

    public Long createPaymentWithGeneratedKey(Payment payment) throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Long primaryKey = null;
        try {
            Connection cw = null;
            try {
                cw = ConnectionPoolManager.getSimpleConnection();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("payment.create");
            ps = cw.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //STUB!!!!
            ps.setBigDecimal(1, payment.getValue());
            ps.execute();
            rs = ps.getGeneratedKeys();
            rs.next();
            primaryKey = rs.getLong(1);

        } finally {
            close(ps);
        }
        return primaryKey;
    }


    @Override
    public Payment update(Payment entity) {
        return null;
    }
}
