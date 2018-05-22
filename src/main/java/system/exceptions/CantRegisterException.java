package system.exceptions;

public class CantRegisterException extends Exception {

    public CantRegisterException(String message, Throwable cause) {
        super(message, cause);
    }

    public CantRegisterException(String message) {
        super(message);
    }

    public CantRegisterException(Throwable cause) {
        super(cause);
    }
}
