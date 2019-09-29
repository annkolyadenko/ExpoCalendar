package ua.com.expo.util.validator.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.expo.file.Regex;
import ua.com.expo.util.validator.IRequestValidator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class RequestValidator implements IRequestValidator {

    private static final Logger LOGGER = LogManager.getLogger(RequestValidator.class.getName());

    private RequestValidator() {
    }

    private static class Holder {
        static final RequestValidator INSTANCE = new RequestValidator();
    }

    public static RequestValidator getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public boolean emailValidate(String email) {
        return (isNotNull(email) && isNotEmpty(email) && regexValidate(email, Regex.EMAIL_REGEX));
    }

    @Override
    public boolean passwordValidate(String password) {
        return (isNotNull(password) && isNotEmpty(password) && regexValidate(password, Regex.PASSWORD_REGEX));
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
    public Long numberParser(String value) {
        Long number = null;
        try {
            number = Long.valueOf(value);
        } catch (NumberFormatException e) {
            LOGGER.error(e);
            throw new RuntimeException(e);
        }
        return number;
    }

    @Override
    public boolean dateValidate(String date) {
        try {
            LocalDate dat = LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            LOGGER.error(e);
            return false;
        }
    }

    @Override
    public boolean dateTimeValidate(String dateTime) {
        try {
            LocalDateTime ldt = LocalDateTime.parse(dateTime);
            return true;
        } catch (DateTimeParseException e) {
            LOGGER.error(e);
            return false;
        }
    }
}
