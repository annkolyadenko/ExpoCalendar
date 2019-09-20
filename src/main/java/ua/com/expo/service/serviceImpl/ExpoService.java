package ua.com.expo.service.serviceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import ua.com.expo.entity.Expo;
import ua.com.expo.entity.Showroom;
import ua.com.expo.entity.Theme;
import ua.com.expo.persistence.dao.factory.AbstractDaoFactory;
import ua.com.expo.persistence.dao.factory.MySqlDaoFactory;
import ua.com.expo.persistence.dao.interfaces.IExpoDao;
import ua.com.expo.persistence.dao.interfaces.IShowroomDao;
import ua.com.expo.persistence.dao.interfaces.IThemeDao;
import ua.com.expo.service.IExpoService;
import ua.com.expo.util.time.IConverter;
import ua.com.expo.util.time.TimeConverter;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;


public class ExpoService implements IExpoService {

    private static final Logger LOGGER = LogManager.getLogger(ExpoService.class.getName());
    private static AbstractDaoFactory factory = MySqlDaoFactory.getInstance();
    private static ModelMapper modelMapper = new ModelMapper();
    private static IConverter converter = new TimeConverter();
    private IExpoDao expoDao;
    private IShowroomDao showroomDao;
    private IThemeDao themeDao;



    @Override
    public List<Expo> findAllExpoByThemeIdAndDate(Long id, Timestamp time) throws SQLException, IOException, ClassNotFoundException {
        expoDao = factory.getExpoDao();
        return expoDao.findAllExpoByThemeIdAndDate(id, time);
    }

    @Override
    public List<Expo> findAllExpoByShowroomId(Long id) throws SQLException, IOException, ClassNotFoundException {
        expoDao = factory.getExpoDao();
        return expoDao.findAllExpoByShowroomId(id);
    }

    @Override
    public List<Expo> findAllExpoByShowroomIdAndDate(Long id, Timestamp time) throws SQLException, IOException, ClassNotFoundException {
        expoDao = factory.getExpoDao();
        return expoDao.findAllExpoByShowroomIdAndDate(id, time);
    }

    @Override
    public boolean createExpo(Long showroomId, Long themeId, String date, Long price, String info) throws SQLException, IOException, ClassNotFoundException, InvalidKeySpecException, NoSuchAlgorithmException {
        expoDao = factory.getExpoDao();
        showroomDao = factory.getShowroomDao();
        themeDao = factory.getThemeDao();
        Showroom showroom = showroomDao.findEntityById(showroomId);
        Theme theme = themeDao.findEntityById(themeId);
        Expo expo = new Expo.Builder().showroom(showroom).theme(theme).date((Instant) converter.convertLocalDateTimeToInstant(LocalDateTime.parse(date))).price(new BigDecimal(price)).info(info).build();
        LOGGER.debug(expo);
        return expoDao.create(expo);
    }

    public static void main(String[] args) {
        String date = "2016-08-16";
        LocalDateTime local = LocalDateTime.parse(date);
        System.out.println(local);

    }
}
