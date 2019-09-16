package ua.com.expo.service.serviceImpl;

import org.modelmapper.ModelMapper;
import ua.com.expo.entity.Expo;
import ua.com.expo.persistence.dao.factory.AbstractDaoFactory;
import ua.com.expo.persistence.dao.factory.MySqlDaoFactory;
import ua.com.expo.persistence.dao.interfaces.IExpoDao;
import ua.com.expo.persistence.dao.interfaces.ITicketDao;
import ua.com.expo.service.IExpoService;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Logger;

public class ExpoService implements IExpoService {

    private static final Logger LOGGER = Logger.getLogger(ExpoService.class.getName());
    private static AbstractDaoFactory factory = MySqlDaoFactory.getInstance();
    private static ModelMapper modelMapper = new ModelMapper();
    private IExpoDao expoDao;


    @Override
    public List<Expo> findAllExpoByThemeIdAndDate(Long id, Timestamp time) throws SQLException, IOException, ClassNotFoundException {
        expoDao = factory.getExpoDao();
        return expoDao.findAllExpoByThemeIdAndDate(id, time);
    }


}
