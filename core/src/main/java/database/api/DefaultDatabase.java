package database.api;

import database.api.exception.UnableToConnectException;
import database.register.ParametersRegister;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

import static java.lang.System.Logger.Level.DEBUG;
import static java.lang.System.getLogger;

public class DefaultDatabase extends Database {
    private static final System.Logger logger = getLogger(DefaultDatabase.class.getSimpleName());

    private String name;

    @Override
    protected void init(CommandLine cmd) throws UnableToConnectException {
        try (Connection connection = getConnection(cmd)) {
            name = connection.getMetaData().getDatabaseProductName();
        } catch (SQLException e) {
            throw new UnableToConnectException(e);
        }
    }

    @Override
    protected boolean isConfigured(CommandLine cmd) {
        Options options = new Options();
        Arrays.stream(cmd.getOptions()).forEach(options::addOption);
        return ParametersRegister.getParameter(DefaultParameters.class).isConfigured(options);
    }

    @Override
    protected boolean canConnect(CommandLine cmd) {
        try (Connection ignored = getConnection(cmd)) {
            return true;
        } catch (SQLException e) {
            logger.log(DEBUG, "Unable to connect through " + getClass().getSimpleName() + ": " + e.getMessage());
            return false;
        }
    }

    @Override
    public String getName() {
        return name;
    }

    private Connection getConnection(CommandLine cmd) throws SQLException {
        String url = cmd.getOptionValue(DefaultParameters.url);
        String username = cmd.getOptionValue(DefaultParameters.username);
        String password = cmd.getOptionValue(DefaultParameters.password);
        return DriverManager.getConnection(url, username, password);
    }
}
