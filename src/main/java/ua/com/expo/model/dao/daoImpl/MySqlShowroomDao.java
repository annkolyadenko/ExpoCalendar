package ua.com.expo.model.dao.daoImpl;

import ua.com.expo.entity.Expo;
import ua.com.expo.entity.Showroom;
import ua.com.expo.entity.Theme;
import ua.com.expo.model.connection.ConnectionPoolManager;
import ua.com.expo.model.dao.interfaces.IExpoDao;
import ua.com.expo.model.dao.interfaces.IShowroomDao;
import ua.com.expo.utils.resource.ConfigurationManager;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MySqlShowroomDao implements IShowroomDao {

    Connection cw;


    @Override
    public List<Showroom> findAll() throws SQLException {
        return null;
    }

    @Override
    public Showroom findEntityById(Long id) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean delete(Showroom showroom) {
        return false;
    }


    public boolean create(Showroom showroom) throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException, IOException, ClassNotFoundException {
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            cw = ConnectionPoolManager.getSimpleConnection();
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("showroom.create");
            ps = cw.prepareStatement(sql);
            ps.setString(1, showroom.getName());
            ps.setString(2, showroom.getInfo());

            ps.executeUpdate();
            flag = true;
        } finally {
            close(ps);
        }
        return false;
    }

    @Override
    public Showroom update(Showroom showroom) {
        return null;
    }
}

