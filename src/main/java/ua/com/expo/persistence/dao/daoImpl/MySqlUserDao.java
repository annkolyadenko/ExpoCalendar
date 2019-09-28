package ua.com.expo.persistence.dao.daoImpl;

import ua.com.expo.entity.User;
import ua.com.expo.entity.enums.RoleEnum;
import ua.com.expo.exception_draft.RuntimeSqlException;
import ua.com.expo.persistence.connection.ConnectionWrapper;
import ua.com.expo.persistence.dao.IUserDao;
import ua.com.expo.persistence.dao.mapper.Mapper;
import ua.com.expo.transaction.util.TransactionUtil;
import ua.com.expo.util.resource.ConfigurationManager;

import java.sql.*;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MySqlUserDao implements IUserDao {

    private static final TransactionUtil transactionUtil = TransactionUtil.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(MySqlUserDao.class.getName());


    public Optional<User> findUserById(Long id) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("user.findById");
            con = transactionUtil.getConnection();
            ps = con.createPreparedStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of((User) Mapper.USER.extractFromResultSet(rs));
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


    public boolean save(User user) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("user.create");
            con = transactionUtil.getConnection();
            ps = con.createPreparedStatement(sql);
            //TODO
            ps.setString(1, RoleEnum.VISITOR.toString());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getLanguage());
            ps.setBytes(5, user.getPassword());
            ps.setBytes(6, user.getSalt());
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
    public Long saveUserWithGeneratedKey(User user) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Long primaryKey = null;
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("user.create");
            con = transactionUtil.getConnection();
            ps = con.createPreparedStatementWithGeneratedKey(sql);
            ps.setString(1, RoleEnum.VISITOR.toString());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getLanguage());
            ps.setBytes(5, user.getPassword());
            ps.setBytes(6, user.getSalt());
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

    @Override
    public boolean saveLanguageByUserId(Long id, String language) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("user.updateLang");
            con = transactionUtil.getConnection();
            ps = con.createPreparedStatement(sql);
            ps.setString(1, language);
            ps.setLong(2, id);
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
    public Optional<User> findUserByEmail(String email) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("user.findByEmail");
            con = transactionUtil.getConnection();
            ps = con.createPreparedStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of((User) Mapper.USER.extractFromResultSet(rs));
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
}
