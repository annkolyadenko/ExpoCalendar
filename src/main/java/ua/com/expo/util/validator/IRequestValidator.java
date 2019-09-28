package ua.com.expo.util.validator;

public interface IRequestValidator {

    boolean emailPasswordValidate(String email, String password);

    boolean isNotNull(String... strings);

    boolean isNotEmpty(String... strings);

    boolean regexValidate(String item, String regex);

    boolean numberValidate(Long number);

    boolean dateValidate();

    boolean timeValidate();

}
