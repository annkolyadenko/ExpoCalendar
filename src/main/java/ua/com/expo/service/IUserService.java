package ua.com.expo.service;

import ua.com.expo.entity.User;
import java.sql.SQLException;


public interface IUserService {

    User findUserByEmail(String email) throws SQLException;

}
