package ua.com.expo.util.validator.impl;

import ua.com.expo.file.Regex;
import ua.com.expo.util.validator.IRequestValidator;

import java.util.Objects;

public class RequestValidator implements IRequestValidator {

    private RequestValidator() {
    }

    private static class Holder {
        static final RequestValidator INSTANCE = new RequestValidator();
    }

    public static RequestValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public boolean emailPasswordValidate(String email, String password) {
        return (isNotNull(email, password) && isNotEmpty(email, password) && regexValidate(email, Regex.EMAIL_REGEX) && regexValidate(password, Regex.PASSWORD_REGEX));
    }

    @Override
    public boolean isNotNull(String... strings) {
        for (String string : strings) {
            if (Objects.isNull(string)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isNotEmpty(String... strings) {
        for (String string : strings) {
            if (string.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean regexValidate(String item, String regex) {
        if (Objects.isNull(item)) {
            return false;
        }
        return item.matches(regex);
    }

    @Override
    public boolean numberValidate(Long number) {
        return false;
    }

    @Override
    public boolean dateValidate() {
        return false;
    }

    @Override
    public boolean timeValidate() {
        return false;
    }
}
