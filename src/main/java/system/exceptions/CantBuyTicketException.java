package system.exceptions;

public class CantBuyTicketException extends Exception {

    public CantBuyTicketException(String message, Throwable cause) {
        super(message, cause);
    }

    public CantBuyTicketException(String message) {
        super(message);
    }

    public CantBuyTicketException(Throwable cause) {
        super(cause);
    }
}
