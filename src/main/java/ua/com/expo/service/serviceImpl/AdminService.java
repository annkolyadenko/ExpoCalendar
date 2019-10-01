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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        List<Expo> expos = expoDao.findAllExpoByShowroomId(id);
        return modelMapper.map(expos, new TypeToken<List<ExpoDto>>() {
        }.getType());
    }

    public List<ExpoDto> findAllExpoByShowroomIdPageable(Long id, Integer limit, Integer currentPage) {
        int offset = currentPage * limit - limit;
        List<Expo> expos = expoDao.findAllExpoByShowroomIdPageable(id, offset, limit);
        return modelMapper.map(expos, new TypeToken<List<ExpoDto>>() {
        }.getType());
    }

    public List<ExpoDto> findAllExpoByShowroomIdAndDate(Long id, Timestamp date) {
        List<Expo> expos = expoDao.findAllExpoByShowroomIdAndDate(id, date);
        return modelMapper.map(expos, new TypeToken<List<ExpoDto>>() {
        }.getType());
    }

    public boolean saveExpo(Long showroomId, Long themeId, String date, Long price, String info) {
        Showroom show = showroomDao.findShowroomById(showroomId).orElseThrow(() -> new RuntimeException("Can't find showroom by id"));
        Theme the = themeDao.findThemeById(themeId).orElseThrow(() -> new RuntimeException("Can't find theme by id"));
        Expo expo = new Expo.Builder().showroom(show).theme(the).date((Instant) converter.convertLocalDateTimeToInstant(LocalDateTime.parse(date))).price(new BigDecimal(price)).info(info).build();
        LOGGER.debug(expo);
        return expoDao.save(expo);
    }

    public List<ShowroomDto> findAllShowroom() {
        List<Showroom> showrooms = showroomDao.findAll();
        return modelMapper.map(showrooms, new TypeToken<List<ShowroomDto>>() {
        }.getType());
    }


    public List<ThemeDto> findAllThemes() {
        List<Theme> themes = themeDao.findAll();
        return modelMapper.map(themes, new TypeToken<List<ShowroomDto>>() {
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

    public LinkedHashMap<ExpoDto, Long> sumAllPurchasedTicketsPageable(Integer limit, Integer currentPage) {
        int offset = currentPage * limit - limit;
        LinkedHashMap<Expo, Long> mapping = ticketDao.sumAllPurchasedTicketsPageable(offset, limit);
        LOGGER.debug(mapping);
        LinkedHashMap<ExpoDto, Long> map = convertMapToDto(mapping);
        LOGGER.debug(map);
        return map;
    }

    public Integer findNumberOfRowsExpos() {
        return expoDao.findNumberOfRows();
    }

    public Integer findNumberOfRowsExposByShowroomId(Long id) {
        return expoDao.findNumberOfRowsExposByShowroomId(id);
    }

    private boolean exist(String theme, List<Theme> themes) {
        for (Theme th : themes) {
            if (th.getName().equals(theme))
                return true;
        }
        return false;
    }

    private LinkedHashMap<ExpoDto, Long> convertMapToDto(Map<Expo, Long> map) {
        LinkedHashMap<ExpoDto, Long> expos = new LinkedHashMap<>();
        map.forEach((k, v) -> expos.put(modelMapper.map(k, ExpoDto.class), v));
        return expos;
    }
}
