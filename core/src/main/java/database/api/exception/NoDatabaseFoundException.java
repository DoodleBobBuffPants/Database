package database.api.exception;

import org.apache.commons.cli.CommandLine;

import static java.lang.System.Logger.Level.DEBUG;
import static java.lang.System.getLogger;

public class NoDatabaseFoundException extends RuntimeException {
    private static final System.Logger logger = getLogger(NoDatabaseFoundException.class.getSimpleName());

    public NoDatabaseFoundException(CommandLine cmd) {
        super("No database found for the current configuration");
        logger.log(DEBUG, cmd);
    }
}
