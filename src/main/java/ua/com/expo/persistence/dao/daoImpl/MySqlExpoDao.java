package ua.com.expo.persistence.dao.daoImpl;

import ua.com.expo.entity.Expo;
import ua.com.expo.entity.Showroom;
import ua.com.expo.entity.Theme;
import ua.com.expo.persistence.connection.ConnectionPoolManager;
import ua.com.expo.persistence.dao.interfaces.IExpoDao;
import ua.com.expo.util.resource.ConfigurationManager;
import ua.com.expo.util.time.TimeConverter;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlExpoDao implements IExpoDao {

    Connection cw;
    private TimeConverter timeConverter = new TimeConverter();

    @Override
    public List<Expo> findAll() throws SQLException {
        return null;
    }

    @Override
    public Expo findEntityById(Long id) throws SQLException, IOException, ClassNotFoundException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Expo expo = new Expo();
        try {
            cw = ConnectionPoolManager.getSimpleConnection();
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("expo.findById");
            ps = cw.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                expo.setId(rs.getLong("expo_id"));
                expo.setShowroom(new Showroom(rs.getLong("showroom_id"), rs.getString("showroom_name"), rs.getString("showroom_info")));
                expo.setTheme(new Theme(rs.getLong("theme_id"), rs.getString("theme_name")));
                expo.setDate(timeConverter.convertToEntity(rs.getTimestamp("expo_date")));
                expo.setPrice(rs.getBigDecimal("expo_ticket_price"));
                expo.setInfo(rs.getString("expo_info"));
            }
            return expo;
        } finally {
            close(ps);
            close(rs);
        }
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean delete(Expo entity) {
        return false;
    }

    @Override
    public boolean create(Expo expo) throws SQLException, IOException, ClassNotFoundException {
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            cw = ConnectionPoolManager.getSimpleConnection();
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("expo.create");
            ps = cw.prepareStatement(sql);
            ps.setLong(1, expo.getShowroom().getId());
            ps.setLong(2, expo.getTheme().getId());
            ps.setTimestamp(3, timeConverter.convertToDatabase(expo.getDate()));
            ps.setBigDecimal(4, expo.getPrice());
            ps.setString(6, expo.getInfo());
            ps.executeUpdate();
            flag = true;
        } finally {
            close(ps);
        }
        return flag;
    }

    @Override
    public Expo update(Expo entity) {
        return null;
    }

    @Override
    public List<Expo> findAllExpoByThemeIdAndDate(Long id, Timestamp time) throws SQLException, IOException, ClassNotFoundException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Expo> expoList = new ArrayList<>();
        try {
            cw = ConnectionPoolManager.getSimpleConnection();
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("expo.findAllByThemeIdAndDate");
            ps = cw.prepareStatement(sql);
            ps.setLong(1, id);
            ps.setTimestamp(2, time);
            rs = ps.executeQuery();
            while (rs.next()) {
                Expo expo = new Expo();
                expo.setId(rs.getLong("expo_id"));
                expo.setShowroom(new Showroom(rs.getLong("showroom_id"), rs.getString("showroom_name"), rs.getString("showroom_info")));
                expo.setTheme(new Theme(rs.getLong("theme_id"), rs.getString("theme_name")));
                expo.setDate(timeConverter.convertToEntity(rs.getTimestamp("expo_date")));
                expo.setPrice(rs.getBigDecimal("expo_ticket_price"));
                expo.setInfo(rs.getString("expo_info"));
                expoList.add(expo);
            }
            return expoList;
        } finally {
            close(ps);
            close(rs);
        }
    }

    @Override
    public List<Expo> findAllExpoByShowroomId(Long id) throws SQLException, IOException, ClassNotFoundException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Expo> expoList = new ArrayList<>();
        try {
            cw = ConnectionPoolManager.getSimpleConnection();
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("expo.findAllExpoByShowroomId");
            ps = cw.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Expo expo = new Expo();
                expo.setId(rs.getLong("expo_id"));
                expo.setShowroom(new Showroom(rs.getLong("showroom_id"), rs.getString("showroom_name"), rs.getString("showroom_info")));
                expo.setTheme(new Theme(rs.getLong("theme_id"), rs.getString("theme_name")));
                expo.setDate(timeConverter.convertToEntity(rs.getTimestamp("expo_date")));
                expo.setPrice(rs.getBigDecimal("expo_ticket_price"));
                expo.setInfo(rs.getString("expo_info"));
                expoList.add(expo);
            }
            return expoList;
        } finally {
            close(ps);
            close(rs);
        }
    }
}
