package ua.com.expo.persistence.dao.daoImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.entity.Theme;
import ua.com.expo.exception_draft.RuntimeSqlException;
import ua.com.expo.persistence.connection.ConnectionWrapper;
import ua.com.expo.persistence.dao.IThemeDao;
import ua.com.expo.persistence.dao.mapper.Mapper;
import ua.com.expo.transaction.TransactionUtil;
import ua.com.expo.util.resource.ConfigurationManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlThemeDao implements IThemeDao {

    private static final Logger LOGGER = LogManager.getLogger(MySqlThemeDao.class.getName());
    private static final TransactionUtil transactionUtil = TransactionUtil.getInstance();

    public List<Theme> findAll() {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Theme> themes = new ArrayList<>();
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("theme.findAll");
            con = transactionUtil.getConnection();
            ps = con.createPreparedStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                themes.add((Theme) Mapper.THEME.extractFromResultSet(rs));
            }
            return themes;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeSqlException(e);
        } finally {
            con.closePreparedStatement(ps);
            con.closeConnection();
        }
    }


    public Optional<Theme> findThemeById(Long id) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("theme.findById");
            con = transactionUtil.getConnection();
            ps = con.createPreparedStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of((Theme) Mapper.THEME.extractFromResultSet(rs));
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

    public boolean save(Theme theme) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("theme.create");
            con = transactionUtil.getConnection();
            ps = con.createPreparedStatement(sql);
            ps.setString(1, theme.getName());
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
}
