package ua.com.expo.persistence.dao.interfaces;

import ua.com.expo.entity.User;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public interface IUserDao extends InterfaceDao<Long, User> {

    User findUserByEmail(String email) throws SQLException;

    Long createUserWithGeneratedKey(User user) throws SQLException, InvalidKeySpecException, NoSuchAlgorithmException;

    boolean updateLanguage(Long userId, String language) throws SQLException, IOException, ClassNotFoundException;

}
