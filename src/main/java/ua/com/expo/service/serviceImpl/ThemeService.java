package ua.com.expo.service.serviceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.entity.Theme;
import ua.com.expo.persistence.dao.IThemeDao;
import ua.com.expo.persistence.dao.factory.AbstractDaoFactory;
import ua.com.expo.persistence.dao.factory.MySqlDaoFactory;
import ua.com.expo.service.IThemeService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.List;

public class ThemeService implements IThemeService {

    private static final Logger LOGGER = LogManager.getLogger(ThemeService.class.getName());
    private final static AbstractDaoFactory factory = MySqlDaoFactory.getInstance();
    private IThemeDao dao;

    @Override
    public List<Theme> findAllThemes() {
        dao = factory.getThemeDao();
        return dao.findAll();
    }

    @Override
    public boolean createTheme(String theme) {
        return dao.save(new Theme.Builder().name(theme).build());
    }
}
