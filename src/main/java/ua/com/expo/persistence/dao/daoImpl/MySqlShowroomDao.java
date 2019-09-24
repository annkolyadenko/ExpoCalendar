package ua.com.expo.persistence.dao.daoImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.entity.Showroom;
import ua.com.expo.exception_draft.RuntimeSqlException;
import ua.com.expo.persistence.connection.ConnectionWrapper;
import ua.com.expo.persistence.dao.IShowroomDao;
import ua.com.expo.persistence.dao.mapper.Mapper;
import ua.com.expo.transaction.TransactionUtil;
import ua.com.expo.util.resource.ConfigurationManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MySqlShowroomDao implements IShowroomDao {

    private static final Logger LOGGER = LogManager.getLogger(MySqlShowroomDao.class.getName());
    private static final TransactionUtil transactionUtil = TransactionUtil.getInstance();

    public List<Showroom> findAll() {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Showroom> list = new ArrayList<>();
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("showroom.findAll");
            con = transactionUtil.getConnection();
            ps = con.createPreparedStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add((Showroom) Mapper.SHOWROOM.extractFromResultSet(rs));
            }
            return list;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeSqlException(e);
        } finally {
            con.closePreparedStatement(ps);
            con.closeConnection();
        }
    }

    public Optional<Showroom> findShowroomById(Long id) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("showroom.findById");
            con = transactionUtil.getConnection();
            ps = con.createPreparedStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of((Showroom) Mapper.SHOWROOM.extractFromResultSet(rs));
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


    public boolean save(Showroom showroom) {
        ConnectionWrapper con = null;
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("showroom.create");
            con = transactionUtil.getConnection();
            ps = con.createPreparedStatement(sql);
            ps.setString(1, showroom.getName());
            ps.setString(2, showroom.getInfo());
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

