package ua.com.expo.exceptions;

public class RuntimeSqlException extends RuntimeException {

    public RuntimeSqlException() { }

    public RuntimeSqlException(String message) {
        super(message);
    }

    public RuntimeSqlException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimeSqlException(Throwable cause) {
        super(cause);
    }
}
