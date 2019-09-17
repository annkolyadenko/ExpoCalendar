package ua.com.expo.service;

import ua.com.expo.entity.Theme;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IThemeService {

    List<Theme> findAllThemes() throws SQLException, IOException, ClassNotFoundException;

}
