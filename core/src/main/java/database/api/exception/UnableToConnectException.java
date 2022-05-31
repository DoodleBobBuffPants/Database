package database.api.exception;

public class UnableToConnectException extends Exception {
    public UnableToConnectException(Exception e) {
        super(e);
    }
}
