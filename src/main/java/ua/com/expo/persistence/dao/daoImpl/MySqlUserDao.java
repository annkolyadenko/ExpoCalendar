package ua.com.expo.persistence.dao.daoImpl;

import ua.com.expo.entity.Role;
import ua.com.expo.entity.User;
import ua.com.expo.persistence.connection.ConnectionPool;
import ua.com.expo.persistence.connection.ConnectionPoolManager;
import ua.com.expo.persistence.dao.interfaces.IUserDao;
import ua.com.expo.util.resource.ConfigurationManager;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.List;
import java.util.logging.Logger;

public class MySqlUserDao implements IUserDao {

    private static final Logger LOGGER = Logger.getLogger(MySqlUserDao.class.getName());

    /*private final TransactionUtil transactionUtil = Mapper.getTransactionUtil();*/
    Connection cw;

    {
        try {
            cw = ConnectionPool.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //TODO
    @Override
    public List<User> findAll() throws SQLException {
        return null;
    }

    @Override
    public User findEntityById(Long id) throws SQLException {
        ResultSet rs = null;
        PreparedStatement ps = null;
        User user = new User();
        try {
            /*ConnectionWrapper cw = transactionUtil.getConnection();*/

            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("user.findById");
            /*ps = cw.createPreparedStatement(sql);*/
            ps = cw.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setId(rs.getLong("user_id"));
                user.setRole(new Role(rs.getLong("role_id"), rs.getString("role_name")));
                user.setName(rs.getString("user_name"));
                user.setEmail(rs.getString("user_email"));
                user.setLanguage(rs.getString("user_lang"));
                user.setPassword(rs.getBytes("user_password"));
                user.setSalt(rs.getBytes("user_salt"));
            }
            return user;
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
    public boolean delete(User user) {
        return false;
    }

    @Override
    public boolean create(User user) throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException {
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
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("user.create");
            //STUB!!!!
            ps.setLong(1, 2);
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getLanguage());
            ps.setBytes(5, user.getPassword());
            ps.setBytes(6, user.getSalt());
            ps.executeUpdate();
            flag = true;
        } finally {
            close(ps);
        }
        return flag;
    }

    public Long createUserWithGeneratedKey(User user) throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Long primaryKey = null;
        boolean flag = false;
        try {
            /*Connection cw = ConnectionPool.getInstance().getConnection();*/
            Connection cw = null;
            try {
                cw = ConnectionPoolManager.getSimpleConnection();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("user.create");
            ps = cw.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //STUB!!!!
            ps.setLong(1, 2);
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getLanguage());
            ps.setBytes(5, user.getPassword());
            ps.setBytes(6, user.getSalt());
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
    public boolean updateLanguage(Long userId, String language) throws SQLException, IOException, ClassNotFoundException {
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            cw = ConnectionPoolManager.getSimpleConnection();
            ps = cw.prepareStatement(ConfigurationManager.SQL_QUERY_MANAGER.getProperty("user.updateLang"));
            ps.setString(1, language);
            ps.setLong(2, userId);
            System.out.println(ps);
            ps.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(ps);
        }
        return flag;
    }


    @Override
    public User update(User entity) {

        return null;
    }

    @Override
    public User findUserByEmail(String email) throws SQLException {
        ResultSet rs = null;
        PreparedStatement ps = null;
        User user = new User();
        try {
            /*ConnectionWrapper cw = transactionUtil.getConnection();*/

            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("user.findByEmail");
            /*ps = cw.createPreparedStatement(sql);*/
            ps = cw.prepareStatement(sql);
            ps.setString(1, email);
            System.out.println("Executed");
            rs = ps.executeQuery();
            if (rs.next()) {
                user.setId(rs.getLong("user_id"));
                System.out.println(user.getId());
                user.setRole(new Role(rs.getLong("role_id"), rs.getString("role_name")));
                user.setName(rs.getString("user_name"));
                user.setEmail(rs.getString("user_email"));
                user.setLanguage(rs.getString("user_lang"));
                user.setPassword(rs.getBytes("user_password"));
                user.setSalt(rs.getBytes("user_salt"));
            } else {
                user = null;
            }
            /*if (rs.next()) {
                return UserMapper.getInstance().extractFromResultSet(rs);
            }*/
            return user;
        } finally {
            //TODO
            close(ps);
            close(rs);
        }
    }
}
