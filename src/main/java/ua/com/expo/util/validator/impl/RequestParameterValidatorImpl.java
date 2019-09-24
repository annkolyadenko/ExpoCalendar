package ua.com.expo.util.validator.impl;

import ua.com.expo.file.Regex;
import ua.com.expo.util.validator.IRequestParametersValidator;

import java.util.Objects;

public class RequestParameterValidatorImpl implements IRequestParametersValidator {

    private RequestParameterValidatorImpl() {
    }

    private static class Holder {
        static final RequestParameterValidatorImpl INSTANCE = new RequestParameterValidatorImpl();
    }

    public static RequestParameterValidatorImpl getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public boolean emailPasswordValidate(String email, String password) {
        return (isNotNull(email, password) && isNotEmpty(email, password) && regexCheck(email, Regex.EMAIL_REGEX) && regexCheck(password, Regex.PASSWORD_REGEX));
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
    public boolean regexCheck(String item, String regex) {
        if (Objects.isNull(item)) {
            return false;
        }
        return item.matches(regex);
    }
}
