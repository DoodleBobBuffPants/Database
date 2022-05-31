package database.api.exception;

import database.api.Configuration;

public class NoDatabaseFoundException extends RuntimeException {
    public NoDatabaseFoundException(Configuration configuration) {
        super("No database found for the current configuration:" + System.lineSeparator() + configuration);
    }
}
