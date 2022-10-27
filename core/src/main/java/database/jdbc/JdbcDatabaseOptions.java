package database.jdbc;

import database.api.DatabaseOptions;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class JdbcDatabaseOptions extends DatabaseOptions {
    public static final Option URL = Option.builder("url").hasArg(true).desc("The JDBC URL for your database").build();
    public static final Option USERNAME = Option.builder("username").hasArg(true).desc("The username for your database").build();
    public static final Option PASSWORD = Option.builder("password").hasArg(true).desc("The password for your database").build();

    @Override
    public Options getAll() {
        return getRequired();
    }

    @Override
    public Options getRequired() {
        return new Options().addOption(URL).addOption(USERNAME).addOption(PASSWORD);
    }
}
