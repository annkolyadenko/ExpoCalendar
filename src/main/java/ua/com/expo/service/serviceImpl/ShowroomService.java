package ua.com.expo.service.serviceImpl;

import ua.com.expo.entity.Showroom;
import ua.com.expo.persistence.dao.factory.AbstractDaoFactory;
import ua.com.expo.persistence.dao.factory.MySqlDaoFactory;
import ua.com.expo.persistence.dao.IShowroomDao;
import ua.com.expo.service.IShowroomService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ShowroomService implements IShowroomService {
    private final static AbstractDaoFactory factory = MySqlDaoFactory.getInstance();
    private IShowroomDao dao;

    @Override
    public List<Showroom> findAllShowroom() throws SQLException, IOException, ClassNotFoundException {
        dao = MySqlDaoFactory.getInstance().getShowroomDao();
        return dao.findAll();
    }
}
