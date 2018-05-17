package system.exceptions;

public class NotFoundInDatabaseException extends Exception {

    public NotFoundInDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundInDatabaseException(String message) {
        super(message);
    }

    public NotFoundInDatabaseException(Throwable cause) {
        super(cause);
    }
}
