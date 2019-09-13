package ua.com.expo.utils.validator;

import ua.com.expo.entity.User;
import ua.com.expo.files.Regex;
import ua.com.expo.utils.security.PasswordHashing;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//TO DO!!!
//All static utils rewrite!!!
public class Valid {

    public static boolean passwordValidator(String password, User user) throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] salt = user.getSalt();
        return Arrays.equals(PasswordHashing.hashGenerator(password, salt), (user.getPassword()));
    }

    public static boolean requestParametersValidator(String email, String password) {
        return (isNotNull(email, password) && isNotEmpty(email, password) && regexCheck(email, Regex.EMAIL_REGEX) && regexCheck(password, Regex.PASSWORD_REGEX));
    }

    private static boolean isNotNull(String... strings) {
        for (String string : strings) {
            if (Objects.isNull(string)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isNotEmpty(String... strings) {
        for (String string : strings) {
            if (string.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    private static boolean regexCheck(String item, String regex) {
        if (Objects.isNull(item)) {
            return false;
        }
        return item.matches(regex);
    }

    //DELETE!!!
    private static boolean requestPasswordValidator(String password) {
        String regex = Regex.PASSWORD_REGEX;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    //DELETE!!!
    private static boolean requestEmailValidator(String email) {
        String regex = Regex.EMAIL_REGEX;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}

