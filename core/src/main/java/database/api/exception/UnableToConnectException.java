package database.api.exception;

public class UnableToConnectException extends RuntimeException {
    public UnableToConnectException(Exception e) {
        super(e);
    }
}
