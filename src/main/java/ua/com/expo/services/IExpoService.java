package ua.com.expo.services;

import ua.com.expo.entity.Expo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IExpoService {

    List<Expo> findAllExpoByThemeId(Long id) throws SQLException, IOException, ClassNotFoundException;
}
