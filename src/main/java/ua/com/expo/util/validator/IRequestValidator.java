package ua.com.expo.util.validator;

public interface IRequestValidator {

    boolean emailValidate(String email);

    boolean passwordValidate(String password);

    boolean isNotNull(String... strings);

    boolean isNotEmpty(String... strings);

    boolean regexValidate(String item, String regex);

    Long numberParser(String number);

    boolean dateValidate(String date);

    boolean dateTimeValidate(String dateTime);

}
