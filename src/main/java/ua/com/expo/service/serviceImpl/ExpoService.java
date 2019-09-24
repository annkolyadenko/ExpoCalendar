package ua.com.expo.service.serviceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import ua.com.expo.entity.Expo;
import ua.com.expo.entity.Showroom;
import ua.com.expo.entity.Theme;
import ua.com.expo.persistence.dao.factory.AbstractDaoFactory;
import ua.com.expo.persistence.dao.factory.MySqlDaoFactory;
import ua.com.expo.persistence.dao.IExpoDao;
import ua.com.expo.persistence.dao.IShowroomDao;
import ua.com.expo.persistence.dao.IThemeDao;
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
import java.util.Optional;


public class ExpoService implements IExpoService {

    private static final Logger LOGGER = LogManager.getLogger(ExpoService.class.getName());
    private static AbstractDaoFactory factory = MySqlDaoFactory.getInstance();
    private static ModelMapper modelMapper = new ModelMapper();
    private static IConverter converter = new TimeConverter();
    private IExpoDao expoDao;
    private IShowroomDao showroomDao;
    private IThemeDao themeDao;


    @Override
    public List<Expo> findAllExpoByThemeIdAndDate(Long id, Timestamp time) {
        expoDao = factory.getExpoDao();
        return expoDao.findAllExpoByThemeIdAndDate(id, time);
    }

    @Override
    public List<Expo> findAllExpoByShowroomId(Long id) {
        expoDao = factory.getExpoDao();
        return expoDao.findAllExpoByShowroomId(id);
    }

    @Override
    public List<Expo> findAllExpoByShowroomIdAndDate(Long id, Timestamp time) {
        expoDao = factory.getExpoDao();
        return expoDao.findAllExpoByShowroomIdAndDate(id, time);
    }

    @Override
    public boolean createExpo(Long showroomId, Long themeId, String date, Long price, String info) {
        expoDao = factory.getExpoDao();
        showroomDao = factory.getShowroomDao();
        themeDao = factory.getThemeDao();
        Optional<Showroom> showroom = showroomDao.findShowroomById(showroomId);
        Showroom show = showroom.orElseThrow(() -> new RuntimeException("Can't find showroom by id"));
        Optional<Theme> theme = themeDao.findThemeById(themeId);
        Theme the = theme.orElseThrow(() -> new RuntimeException("Can't find theme by id"));
        Expo expo = new Expo.Builder().showroom(show).theme(the).date((Instant) converter.convertLocalDateTimeToInstant(LocalDateTime.parse(date))).price(new BigDecimal(price)).info(info).build();
        LOGGER.debug(expo);
        return expoDao.save(expo);
    }
}
