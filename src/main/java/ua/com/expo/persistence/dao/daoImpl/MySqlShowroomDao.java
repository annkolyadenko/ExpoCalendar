package ua.com.expo.persistence.dao.daoImpl;

import ua.com.expo.entity.Showroom;
import ua.com.expo.persistence.connection.ConnectionPoolManager;
import ua.com.expo.persistence.dao.interfaces.IShowroomDao;
import ua.com.expo.util.resource.ConfigurationManager;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlShowroomDao implements IShowroomDao {

    Connection cw;


    @Override
    public List<Showroom> findAll() throws SQLException, IOException, ClassNotFoundException {
        List<Showroom> list = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            cw = ConnectionPoolManager.getSimpleConnection();
            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("showroom.findAll");
            ps = cw.prepareStatement(sql);
            System.out.println(ps);
            rs = ps.executeQuery();
            System.out.println(rs);
            while (rs.next()) {
                Showroom showroom = new Showroom();
                showroom.setId(rs.getLong("showroom_id"));
                showroom.setName(rs.getString("showroom_name"));
                showroom.setInfo(rs.getString("showroom_info"));
                System.out.println(list.size() + "Check");
                list.add(showroom);
            }
            return list;
        } finally {
            close(ps);
        }
    }

    @Override
    public Showroom findEntityById(Long id) throws SQLException, IOException, ClassNotFoundException {
        ResultSet rs = null;
        PreparedStatement ps = null;
        Showroom showroom = new Showroom();
        try {
            /*ConnectionWrapper cw = transactionUtil.getConnection();*/

            String sql = ConfigurationManager.SQL_QUERY_MANAGER.getProperty("showroom.findById");
            /*ps = cw.createPreparedStatement(sql);*/
            cw = ConnectionPoolManager.getSimpleConnection();
            ps = cw.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                showroom.setId(rs.getLong("showroom_id"));
                showroom.setName(rs.getString("showroom_name"));
                showroom.setInfo(rs.getString("showroom_info"));

            }
            return showroom;
            /*if (rs.next()) {
                return UserMapper.getInstance().extractFromResultSet(rs);
            }*/
        } finally {
            //TODO
            close(ps);
        }
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

