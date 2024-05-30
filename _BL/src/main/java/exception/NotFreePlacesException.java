package exception;

public class NotFreePlacesException extends RuntimeException {
    public NotFreePlacesException() {
        super();
    }

    public NotFreePlacesException(String message) {
        super(message);
    }
}
