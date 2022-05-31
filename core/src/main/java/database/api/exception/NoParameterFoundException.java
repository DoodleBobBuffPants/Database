package database.api.exception;

import org.apache.commons.cli.Option;

public class NoParameterFoundException extends RuntimeException {
    public NoParameterFoundException(Option parameter) {
        super("Parameter '" + parameter.getArgName() + "' is not configured");
    }
}
