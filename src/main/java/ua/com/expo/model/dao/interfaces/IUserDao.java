package ua.com.expo.model.dao.interfaces;

import ua.com.expo.entity.User;

import java.sql.SQLException;

public interface IUserDao extends InterfaceDao<Long, User> {

    User findUserByEmail(String email) throws SQLException;


}
