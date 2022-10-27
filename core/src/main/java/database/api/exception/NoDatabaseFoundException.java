package database.api.exception;

import database.api.DatabaseConfiguration;

public class NoDatabaseFoundException extends RuntimeException {
    public NoDatabaseFoundException(DatabaseConfiguration configuration) {
        super("No database found for the current configuration:" + System.lineSeparator() + configuration);
    }
}
