package database.api;

import database.api.exception.NoDatabaseFoundException;
import database.api.exception.UnableToConnectException;
import database.register.DatabaseRegister;
import org.apache.commons.cli.CommandLine;

import java.sql.SQLException;

public abstract class Database {
    public static Database connect(CommandLine cmd) throws UnableToConnectException {
        Database database = DatabaseRegister.getDatabases().stream()
                .filter(d -> d.isConfigured(cmd))
                .filter(d -> d.canConnect(cmd))
                .findFirst()
                .orElseThrow(() -> new NoDatabaseFoundException(cmd));
        database.init(cmd);
        return database;
    }

    protected abstract void init(CommandLine cmd) throws UnableToConnectException;

    protected abstract boolean isConfigured(CommandLine cmd);

    protected abstract boolean canConnect(CommandLine cmd);

    public abstract String getName();
}
