package repository;

public class ObjectNotFountException extends RuntimeException {
    public ObjectNotFountException() {
        super();
    }

    public ObjectNotFountException(String message) {
        super(message);
    }
}
