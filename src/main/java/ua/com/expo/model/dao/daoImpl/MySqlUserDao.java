package ua.com.expo.model.dao.daoImpl;

import ua.com.expo.entity.Role;
import ua.com.expo.entity.User;
import ua.com.expo.model.connection.ConnectionPool;
import ua.com.expo.model.connection.ConnectionPoolManager;
import ua.com.expo.model.dao.interfaces.IUserDao;
import ua.com.expo.utils.resource.ConfigurationManager;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MySqlUserDao implements IUserDao {

    /*private final TransactionUtil transactionUtil = Mapper.getTransactionUtil();*/
    Connection cw;

    {
        try {
            cw = ConnectionPool.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //TO DO!!!
    @Override
    public List<User> findAll() throws SQLException {
        return null;
    }

    @Override
    public User findEntityById(Long id) {
        return null;
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
            System.out.println(cw);
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("user.create");
            ps = cw.prepareStatement(sql);
            //STUB!!!!
            ps.setLong(1, 2);
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setBytes(4, user.getPassword());
            ps.setBytes(5, user.getSalt());
            //TO DO!!! Нужно передавать char []
            /*ps.setBytes(4, PasswordHashing.hashGenerator(entity.getPassword()));*/
            ps.executeUpdate();
            flag = true;
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
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setId(rs.getLong("user_id"));
                user.setRole(new Role(rs.getLong("role_id"), rs.getString("role_name")));
                user.setName(rs.getString("user_name"));
                user.setEmail(rs.getString("user_email"));
                user.setPassword(rs.getBytes("user_password"));
                user.setSalt(rs.getBytes("user_salt"));
            }
            return user;
            /*if (rs.next()) {
                return UserMapper.getInstance().extractFromResultSet(rs);
            }*/
        } finally {
            //TO DO!!!
            close(ps);
            close(rs);
        }
        //TO DO!!!
        /*return null;*/
    }
}
