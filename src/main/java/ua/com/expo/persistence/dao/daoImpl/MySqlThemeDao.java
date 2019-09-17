package ua.com.expo.persistence.dao.daoImpl;

import ua.com.expo.entity.Theme;
import ua.com.expo.persistence.connection.ConnectionPool;
import ua.com.expo.persistence.connection.ConnectionPoolManager;
import ua.com.expo.persistence.dao.interfaces.IThemeDao;
import ua.com.expo.util.resource.ConfigurationManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Temporal: connection solving problem!
 */
public class MySqlThemeDao implements IThemeDao {

    Connection cw;

    {
        try {
            cw = ConnectionPool.getInstance().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Theme> findAll() throws SQLException {
        List<Theme> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("theme.findAll");
        ps = cw.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            Theme theme = new Theme();
            theme.setId(rs.getLong("theme_id"));
            theme.setName(rs.getString("theme_name"));
            list.add(theme);
        }
        return list;
    }

    @Override
    public Theme findEntityById(Long id) {
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean delete(Theme entity) {
        return false;
    }

    @Override
    public boolean create(Theme theme) throws SQLException, IOException, ClassNotFoundException {
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            cw = ConnectionPoolManager.getSimpleConnection();
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("theme.create");
            ps = cw.prepareStatement(sql);
            ps.setString(1, theme.getName());
            ps.executeUpdate();
            flag = true;
        } finally {
            close(ps);
        }
        return flag;
    }

    @Override
    public Theme update(Theme entity) {
        return null;
    }
}
