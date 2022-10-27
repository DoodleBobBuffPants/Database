package database.cli;

import database.api.DatabaseOptions;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class CliDatabaseOptions extends DatabaseOptions {
    public static final Option HELP = Option.builder("help").desc("Print the help output").build();

    @Override
    public Options getAll() {
        return getRequired().addOption(HELP);
    }

    @Override
    public Options getRequired() {
        return new Options();
    }
}
