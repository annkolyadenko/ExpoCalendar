package ua.com.expo.files;

public interface Regex {
    
    String EMAIL_REGEX = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    String PASSWORD_REGEX = "^[a-z0-9_-]{3,64}$";
}

