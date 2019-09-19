package ua.com.expo.service;

import ua.com.expo.entity.Expo;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface IExpoService {

    List<Expo> findAllExpoByThemeIdAndDate(Long id, Timestamp time) throws SQLException, IOException, ClassNotFoundException;

    List<Expo> findAllExpoByShowroomId(Long id) throws SQLException, IOException, ClassNotFoundException;

    List<Expo> findAllExpoByShowroomIdAndDate(Long id, Timestamp time) throws SQLException, IOException, ClassNotFoundException;

    boolean createExpo(Long showroomId, Long themeId, String date, Long price, String info) throws SQLException, IOException, ClassNotFoundException, InvalidKeySpecException, NoSuchAlgorithmException;

}
