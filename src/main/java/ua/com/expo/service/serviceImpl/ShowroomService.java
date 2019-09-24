package ua.com.expo.service.serviceImpl;

import ua.com.expo.entity.Showroom;
import ua.com.expo.persistence.dao.factory.AbstractDaoFactory;
import ua.com.expo.persistence.dao.factory.MySqlDaoFactory;
import ua.com.expo.persistence.dao.IShowroomDao;

import java.util.List;

public class ShowroomService {
    private final static AbstractDaoFactory factory = MySqlDaoFactory.getInstance();
    private IShowroomDao dao;

    public List<Showroom> findAllShowroom() {
        dao = MySqlDaoFactory.getInstance().getShowroomDao();
        return dao.findAll();
    }
}
