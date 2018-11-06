package by.iba.boot_learning.validator.exception;

public class NotValidDataException extends RuntimeException {
    public NotValidDataException(String message) {
        super(message);
    }

    public NotValidDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotValidDataException(Throwable cause) {
        super(cause);
    }
}
