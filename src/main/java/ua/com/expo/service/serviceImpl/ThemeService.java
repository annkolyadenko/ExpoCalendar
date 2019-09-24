package ua.com.expo.service.serviceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.entity.Theme;
import ua.com.expo.persistence.dao.IThemeDao;
import ua.com.expo.persistence.dao.factory.AbstractDaoFactory;
import ua.com.expo.persistence.dao.factory.MySqlDaoFactory;

import java.util.List;

public class ThemeService {

    private static final Logger LOGGER = LogManager.getLogger(ThemeService.class.getName());
    private final static AbstractDaoFactory factory = MySqlDaoFactory.getInstance();
    private IThemeDao dao;

    public List<Theme> findAllThemes() {
        dao = factory.getThemeDao();
        return dao.findAll();
    }

    public boolean createTheme(String theme) {
        return dao.save(new Theme.Builder().name(theme).build());
    }
}
