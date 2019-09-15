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
    public Payment findEntityById(Long id) {
        return null;
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
            ps.setString(2, payment.getType());
            ps.setString(3, payment.getStatus());
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
            ps.setString(2, payment.getType());
            ps.setString(3, payment.getStatus());
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
