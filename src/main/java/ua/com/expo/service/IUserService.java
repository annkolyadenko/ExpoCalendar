package ua.com.expo.service;

import ua.com.expo.entity.User;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;


public interface IUserService {

    User findUserByEmail(String email) throws SQLException;

    User signUpUser(String name, String email, String password) throws InvalidKeySpecException, NoSuchAlgorithmException, SQLException, IOException, ClassNotFoundException;

}
