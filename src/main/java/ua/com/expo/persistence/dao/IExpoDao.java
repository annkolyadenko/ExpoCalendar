package ua.com.expo.persistence.dao;

import ua.com.expo.entity.Expo;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface IExpoDao {

    List<Expo> findAllExpoByThemeIdAndDate(Long id, Timestamp date);

    List<Expo> findAllExpoByShowroomId(Long id);

    List<Expo> findAllExpoByShowroomIdAndDate(Long id, Timestamp date);

    boolean save(Expo expo);

    Optional<Expo> findExpoById(Long id);

}
