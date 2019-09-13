package ua.com.expo.services.servicesImpl;

import ua.com.expo.entity.Theme;
import ua.com.expo.persistence.dao.interfaces.IThemeDao;
import ua.com.expo.persistence.dao.factory.AbstractDaoFactory;
import ua.com.expo.persistence.dao.factory.MySqlDaoFactory;
import ua.com.expo.services.IThemeService;

import java.sql.SQLException;
import java.util.List;

public class ThemeService implements IThemeService {

    private final static AbstractDaoFactory factory = MySqlDaoFactory.getInstance();
    private IThemeDao dao;

    @Override
    public List<Theme> findAllThemes() throws SQLException {
        dao = factory.getThemeDao();
        return dao.findAll();
    }
}
