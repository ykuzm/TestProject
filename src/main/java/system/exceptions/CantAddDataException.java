package system.exceptions;

public class CantAddDataException extends Exception {

    public CantAddDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public CantAddDataException(String message) {
        super(message);
    }

    public CantAddDataException(Throwable cause) {
        super(cause);
    }
}
