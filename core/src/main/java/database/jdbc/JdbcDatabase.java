package database.jdbc;

import database.api.Database;
import database.api.DatabaseConfiguration;
import database.api.exception.UnableToConnectException;
import database.register.DatabaseOptionsRegister;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static database.jdbc.JdbcDatabaseOptions.*;
import static java.lang.System.Logger.Level.DEBUG;
import static java.lang.System.getLogger;

public class JdbcDatabase extends Database {
    private static final System.Logger logger = getLogger(JdbcDatabase.class.getSimpleName());

    private String name;

    @Override
    protected boolean isConfigured(DatabaseConfiguration configuration) {
        return DatabaseOptionsRegister.get(JdbcDatabaseOptions.class).isConfigured(configuration);
    }

    @Override
    protected boolean canConnect(DatabaseConfiguration configuration) {
        try (Connection ignored = getConnection(configuration)) {
            return true;
        } catch (SQLException e) {
            logger.log(DEBUG, "Unable to connect using " + getClass().getSimpleName() + ": " + e.getMessage());
            return false;
        }
    }

    @Override
    protected void init(DatabaseConfiguration configuration) {
        try (Connection connection = getConnection(configuration)) {
            name = connection.getMetaData().getDatabaseProductName();
        } catch (SQLException e) {
            throw new UnableToConnectException(e);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    private Connection getConnection(DatabaseConfiguration configuration) throws SQLException {
        return DriverManager.getConnection(configuration.get(URL), configuration.get(USERNAME), configuration.get(PASSWORD));
    }
}
