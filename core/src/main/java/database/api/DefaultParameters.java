package database.api;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class DefaultParameters implements Parameters {
    public static Option url = new Option("url", true, "The JDBC URL for your database");
    public static Option username = new Option("username", true, "The username for your database");
    public static Option password = new Option("password", true, "The password for your database");

    @Override
    public Options getAll() {
        return new Options()
                .addOption(url)
                .addOption(username)
                .addOption(password);
    }

    @Override
    public boolean isConfigured(Options options) {
        return options.hasOption(url.getOpt()) &&
               options.hasOption(username.getOpt()) &&
               options.hasOption(password.getOpt());
    }
}
