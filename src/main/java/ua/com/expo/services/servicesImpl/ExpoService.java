package ua.com.expo.services.servicesImpl;

import org.modelmapper.ModelMapper;
import ua.com.expo.entity.Expo;
import ua.com.expo.persistence.dao.factory.AbstractDaoFactory;
import ua.com.expo.persistence.dao.factory.MySqlDaoFactory;
import ua.com.expo.persistence.dao.interfaces.IExpoDao;
import ua.com.expo.services.IExpoService;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class ExpoService implements IExpoService {

    private static AbstractDaoFactory factory = MySqlDaoFactory.getInstance();
    private static ModelMapper modelMapper = new ModelMapper();
    private IExpoDao dao;

    @Override
    public List<Expo> findAllExpoByThemeIdAndDate(Long id, Timestamp time) throws SQLException, IOException, ClassNotFoundException {
        dao = factory.getExpoDao();
        return dao.findAllExpoByThemeIdAndDate(id, time);
    }
}
