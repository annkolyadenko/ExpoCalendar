package ua.com.expo.model.dao.interfaces;

import ua.com.expo.entity.Expo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IExpoDao extends InterfaceDao<Long, Expo> {

    List<Expo> findAllExpoByThemeId(Long id) throws SQLException, IOException, ClassNotFoundException;
}
