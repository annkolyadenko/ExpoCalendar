package ua.com.expo.service.serviceImpl;

import ua.com.expo.entity.Theme;
import ua.com.expo.persistence.dao.interfaces.IThemeDao;
import ua.com.expo.persistence.dao.factory.AbstractDaoFactory;
import ua.com.expo.persistence.dao.factory.MySqlDaoFactory;
import ua.com.expo.service.IThemeService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.List;

public class ThemeService implements IThemeService {

    private final static AbstractDaoFactory factory = MySqlDaoFactory.getInstance();
    private IThemeDao dao;

    @Override
    public List<Theme> findAllThemes() throws SQLException, IOException, ClassNotFoundException {
        dao = factory.getThemeDao();
        return dao.findAll();
    }

    @Override
    public boolean createTheme(String theme) throws NoSuchAlgorithmException, SQLException, ClassNotFoundException, InvalidKeySpecException, IOException {
        return dao.create(new Theme.Builder().name(theme).build());
    }
}
