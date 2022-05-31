package database.api;

import database.api.exception.UnableToConnectException;
import database.register.ParametersRegister;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.System.Logger.Level.DEBUG;
import static java.lang.System.getLogger;

public class DefaultDatabase extends Database {
    private static final System.Logger logger = getLogger(DefaultDatabase.class.getSimpleName());

    private String name;

    @Override
    protected void init(Configuration configuration) throws UnableToConnectException {
        try (Connection connection = getConnection(configuration)) {
            name = connection.getMetaData().getDatabaseProductName();
        } catch (SQLException e) {
            throw new UnableToConnectException(e);
        }
    }

    @Override
    protected boolean isConfigured(Configuration configuration) {
        return ParametersRegister.getParameter(DefaultParameters.class).isConfigured(configuration.toOptions());
    }

    @Override
    protected boolean canConnect(Configuration configuration) {
        try (Connection ignored = getConnection(configuration)) {
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

    private Connection getConnection(Configuration configuration) throws SQLException {
        return DriverManager.getConnection(configuration.get(DefaultParameters.url), configuration.get(DefaultParameters.username), configuration.get(DefaultParameters.password));
    }
}
