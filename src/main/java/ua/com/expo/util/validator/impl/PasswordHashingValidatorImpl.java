package ua.com.expo.util.validator.impl;

import ua.com.expo.entity.User;
import ua.com.expo.util.security.IPasswordHashing;
import ua.com.expo.util.security.impl.PasswordHashingImpl;
import ua.com.expo.util.validator.IPasswordHashingValidator;

import java.util.Arrays;

public class PasswordHashingValidatorImpl implements IPasswordHashingValidator {

    private final IPasswordHashing hashing = PasswordHashingImpl.getInstance();

    private PasswordHashingValidatorImpl() { }

    private static class Holder {
        static final PasswordHashingValidatorImpl INSTANCE = new PasswordHashingValidatorImpl();
    }

    public static PasswordHashingValidatorImpl getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public boolean passwordValidate(String password, User user) {
        byte[] salt = user.getSalt();
        return Arrays.equals(hashing.hashGenerator(password, salt), (user.getPassword()));
    }
}
