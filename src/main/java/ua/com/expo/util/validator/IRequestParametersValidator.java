package ua.com.expo.util.validator;

public interface IRequestParametersValidator {

    boolean emailPasswordValidate(String email, String password);

    boolean isNotNull(String... strings);

    boolean isNotEmpty(String... strings);

    boolean regexCheck(String item, String regex);
}
