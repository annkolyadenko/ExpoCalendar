package ua.com.expo.service.serviceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import ua.com.expo.controller.context.Context;
import ua.com.expo.dto.ExpoDto;
import ua.com.expo.dto.ShowroomDto;
import ua.com.expo.dto.ThemeDto;
import ua.com.expo.entity.Expo;
import ua.com.expo.entity.Showroom;
import ua.com.expo.entity.Theme;
import ua.com.expo.persistence.dao.IExpoDao;
import ua.com.expo.persistence.dao.IShowroomDao;
import ua.com.expo.persistence.dao.IThemeDao;
import ua.com.expo.persistence.dao.ITicketDao;
import ua.com.expo.persistence.dao.factory.AbstractDaoFactory;
import ua.com.expo.util.time.IDateConverter;
import ua.com.expo.util.time.impl.DateConverter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AdminService {
    private static final Logger LOGGER = LogManager.getLogger(AdminService.class.getName());
    private static ModelMapper modelMapper = new ModelMapper();
    private static IDateConverter converter = DateConverter.getInstance();
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

    public List<ExpoDto> findAllExpoByShowroomId(Long id) {
        return modelMapper.map(expoDao.findAllExpoByShowroomId(id), new TypeToken<List<ExpoDto>>() {
        }.getType());
    }

    public List<ExpoDto> findAllExpoByShowroomIdAndDate(Long id, Timestamp date) {
        return modelMapper.map(expoDao.findAllExpoByShowroomIdAndDate(id, date), new TypeToken<List<ExpoDto>>() {
        }.getType());
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

    public List<ShowroomDto> findAllShowroom() {
        return modelMapper.map(showroomDao.findAll(), new TypeToken<List<ShowroomDto>>() {
        }.getType());
    }

    public List<ThemeDto> findAllThemes() {
        return modelMapper.map(themeDao.findAll(), new TypeToken<List<ShowroomDto>>() {
        }.getType());
    }

    public boolean saveTheme(String theme) {
        List<Theme> themes = themeDao.findAll();
        if (!exist(theme, themes)) {
            return themeDao.save(new Theme.Builder().name(theme).build());
        }
        return false;
    }

    public Long sumPurchasedTicketsByExpoId(Long expoId) {
        return ticketDao.sumPurchasedTicketsByExpoId(expoId);
    }

    public Map<ExpoDto, Long> sumAllPurchasedTickets() {
        Map<Expo, Long> mapping = ticketDao.sumAllPurchasedTickets();
        LOGGER.debug(mapping);
        Map<ExpoDto, Long> map = convertMapToDto(mapping);
        LOGGER.debug(map);
        return map;
    }

    private boolean exist(String theme, List<Theme> themes) {
        for (Theme th : themes) {
            if (th.getName().equals(theme))
                return true;
        }
        return false;
    }

    private Map<ExpoDto, Long> convertMapToDto(Map<Expo, Long> map) {
        Map<ExpoDto, Long> result = new HashMap<>();
        map.forEach((k, v) -> result.put(modelMapper.map(k, ExpoDto.class), v));
        return result;
    }
}
