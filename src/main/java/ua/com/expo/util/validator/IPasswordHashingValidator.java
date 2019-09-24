package ua.com.expo.util.validator;

import ua.com.expo.entity.User;

public interface IPasswordHashingValidator {

    boolean passwordValidate(String password, User user);
}
