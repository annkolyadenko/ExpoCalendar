package ua.com.expo.exception_draft;

public class RuntimeServiceException extends RuntimeException {

    public RuntimeServiceException() {
    }

    public RuntimeServiceException(String message) {
        super(message);
    }

    public RuntimeServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RuntimeServiceException(Throwable cause) {
        super(cause);
    }
}
