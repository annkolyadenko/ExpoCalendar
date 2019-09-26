package ua.com.expo.service.serviceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import ua.com.expo.controller.context.Context;
import ua.com.expo.entity.Expo;
import ua.com.expo.entity.Showroom;
import ua.com.expo.entity.Theme;
import ua.com.expo.persistence.dao.IExpoDao;
import ua.com.expo.persistence.dao.IShowroomDao;
import ua.com.expo.persistence.dao.IThemeDao;
import ua.com.expo.persistence.dao.ITicketDao;
import ua.com.expo.persistence.dao.factory.AbstractDaoFactory;
import ua.com.expo.persistence.dao.factory.MySqlDaoFactory;
import ua.com.expo.util.time.IConverter;
import ua.com.expo.util.time.TimeConverter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class AdminService {
    private static final Logger LOGGER = LogManager.getLogger(AdminService.class.getName());
    private static ModelMapper modelMapper = new ModelMapper();
    private static IConverter converter = new TimeConverter();
    private IExpoDao expoDao;
    private IShowroomDao showroomDao;
    private IThemeDao themeDao;
    private ITicketDao ticketDao;

    public AdminService() {
        AbstractDaoFactory daoFactory = Context.getInstance().getMySqlDaoFactory();
        this.expoDao = daoFactory.getExpoDao();
        this.showroomDao = daoFactory.getShowroomDao();
        this.themeDao = daoFactory.getThemeDao();
        this.ticketDao = daoFactory.getTicketDao();
    }

    public List<Expo> findAllExpoByShowroomId(Long id) {
        return expoDao.findAllExpoByShowroomId(id);
    }

    public List<Expo> findAllExpoByShowroomIdAndDate(Long id, Timestamp time) {
        return expoDao.findAllExpoByShowroomIdAndDate(id, time);
    }

    public boolean saveExpo(Long showroomId, Long themeId, String date, Long price, String info) {
        Optional<Showroom> showroom = showroomDao.findShowroomById(showroomId);
        Showroom show = showroom.orElseThrow(() -> new RuntimeException("Can't find showroom by id"));
        Optional<Theme> theme = themeDao.findThemeById(themeId);
        Theme the = theme.orElseThrow(() -> new RuntimeException("Can't find theme by id"));
        Expo expo = new Expo.Builder().showroom(show).theme(the).date((Instant) converter.convertLocalDateTimeToInstant(LocalDateTime.parse(date))).price(new BigDecimal(price)).info(info).build();
        LOGGER.debug(expo);
        return expoDao.save(expo);
    }

    public List<Showroom> findAllShowroom() {
        showroomDao = MySqlDaoFactory.getInstance().getShowroomDao();
        return showroomDao.findAll();
    }

    public List<Theme> findAllThemes() {
        return themeDao.findAll();
    }

    public boolean saveTheme(String theme) {
        return themeDao.save(new Theme.Builder().name(theme).build());
    }

    public Long sumPurchasedTicketsByExpoId(Long expoId) {
        return ticketDao.sumPurchasedTicketsByExpoId(expoId);
    }
}
