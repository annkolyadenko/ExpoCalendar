package ua.com.expo.persistence.dao.daoImpl;

import ua.com.expo.entity.Expo;
import ua.com.expo.exception_draft.RuntimeSqlException;
import ua.com.expo.persistence.connection.ConnectionWrapper;
import ua.com.expo.persistence.dao.IExpoDao;
import ua.com.expo.persistence.dao.mapper.Mapper;
import ua.com.expo.transaction.TransactionUtil;
import ua.com.expo.util.resource.ConfigurationManager;
import ua.com.expo.util.time.TimeConverter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MySqlExpoDao implements IExpoDao {

    private TimeConverter timeConverter = new TimeConverter();
    private static final Logger LOGGER = LogManager.getLogger(MySqlExpoDao.class.getName());
    private static final TransactionUtil transactionUtil = TransactionUtil.getInstance();


    public Optional<Expo> findExpoById(Long id) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("expo.findById");
            con = transactionUtil.getConnection();
            ps = con.createPreparedStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of((Expo) Mapper.EXPO.extractFromResultSet(rs));
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

    public boolean save(Expo expo) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("expo.create");
            con = transactionUtil.getConnection();
            ps = con.createPreparedStatement(sql);
            ps.setLong(1, expo.getShowroom().getId());
            ps.setLong(2, expo.getTheme().getId());
            ps.setTimestamp(3, timeConverter.convertToDatabase(expo.getDate()));
            ps.setBigDecimal(4, expo.getPrice());
            ps.setString(5, expo.getInfo());
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
    public List<Expo> findAllExpoByThemeIdAndDate(Long id, Timestamp time) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Expo> expos = new ArrayList<>();
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("expo.findAllByThemeIdAndDate");
            con = transactionUtil.getConnection();
            ps = con.createPreparedStatement(sql);
            ps.setLong(1, id);
            ps.setTimestamp(2, time);
            rs = ps.executeQuery();
            while (rs.next()) {
                expos.add((Expo) Mapper.EXPO.extractFromResultSet(rs));
            }
            return expos;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeSqlException(e);
        } finally {
            con.closePreparedStatement(ps);
            con.closeConnection();
        }
    }

    @Override
    public List<Expo> findAllExpoByShowroomId(Long id) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Expo> expos = new ArrayList<>();
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("expo.findAllByShowroomId");
            con = transactionUtil.getConnection();
            ps = con.createPreparedStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                expos.add((Expo) Mapper.EXPO.extractFromResultSet(rs));
            }
            return expos;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeSqlException(e);
        } finally {
            con.closePreparedStatement(ps);
            con.closeConnection();
        }
    }

    @Override
    public List<Expo> findAllExpoByShowroomIdAndDate(Long id, Timestamp time) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Expo> expoList = new ArrayList<>();
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("expo.findAllByShowroomIdAndDate");
            con = transactionUtil.getConnection();
            ps = con.createPreparedStatement(sql);
            ps.setLong(1, id);
            ps.setTimestamp(2, time);
            rs = ps.executeQuery();
            while (rs.next()) {
                expoList.add((Expo) Mapper.EXPO.extractFromResultSet(rs));
            }
            return expoList;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeSqlException(e);
        } finally {
            con.closePreparedStatement(ps);
            con.closeConnection();
        }
    }
}
