package repository;

public class ConnectionException extends RuntimeException {
    public ConnectionException() {
        super();
    }

    public ConnectionException(String message) {
        super(message);
    }
}
