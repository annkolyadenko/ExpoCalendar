package ua.com.expo.util.validator.impl;

import ua.com.expo.entity.User;
import ua.com.expo.util.security.IPasswordHashing;
import ua.com.expo.util.security.impl.PasswordHashingImpl;
import ua.com.expo.util.validator.IPasswordValidator;

import java.util.Arrays;

public class PasswordValidator implements IPasswordValidator {

    private final IPasswordHashing hashing = PasswordHashingImpl.getInstance();

    private PasswordValidator() { }

    private static class Holder {
        static final PasswordValidator INSTANCE = new PasswordValidator();
    }

    public static PasswordValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public boolean passwordValidate(String password, User user) {
        byte[] salt = user.getSalt();
        return Arrays.equals(hashing.hashGenerator(password, salt), (user.getPassword()));
    }
}
