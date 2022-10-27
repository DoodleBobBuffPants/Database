package database.api.exception;

import org.apache.commons.cli.Option;

public class OptionNotFoundException extends RuntimeException {
    public OptionNotFoundException(Option option) {
        super("Option '" + option.getOpt() + "' is not configured");
    }
}
