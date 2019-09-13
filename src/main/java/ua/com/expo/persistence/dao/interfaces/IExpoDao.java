package ua.com.expo.persistence.dao.interfaces;

import ua.com.expo.entity.Expo;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public interface IExpoDao extends InterfaceDao<Long, Expo> {

    List<Expo> findAllExpoByThemeIdAndDate(Long id, Timestamp time) throws SQLException, IOException, ClassNotFoundException;
}
