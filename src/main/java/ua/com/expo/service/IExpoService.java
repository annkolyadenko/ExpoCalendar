package ua.com.expo.service;

import ua.com.expo.entity.Expo;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface IExpoService {

    List<Expo> findAllExpoByThemeIdAndDate(Long id, Timestamp time) throws SQLException, IOException, ClassNotFoundException;

    List<Expo> findAllExpoByShowroomId(Long id) throws SQLException, IOException, ClassNotFoundException;
}
