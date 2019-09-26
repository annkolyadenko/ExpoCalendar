package ua.com.expo.persistence.dao;

import ua.com.expo.entity.User;

import java.util.Optional;

public interface IUserDao {

    Optional<User> findUserByEmail(String email);

    Long saveUserWithGeneratedKey(User user);

    boolean saveLanguageByUserId(Long id, String language);

    boolean save(User user);

    Optional<User> findUserById(Long id);

}
