package ua.com.expo.services;

import ua.com.expo.entity.Theme;

import java.sql.SQLException;
import java.util.List;

public interface IThemeService {

    List<Theme> findAllThemes() throws SQLException;

}
