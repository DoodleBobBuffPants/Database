package database.api;

import database.api.exception.NoDatabaseFoundException;
import database.api.exception.UnableToConnectException;
import database.register.DatabaseRegister;

public abstract class Database {
    static Database connect(Configuration configuration) throws UnableToConnectException {
        Database database = DatabaseRegister.getDatabases().stream()
                .filter(d -> d.isConfigured(configuration))
                .filter(d -> d.canConnect(configuration))
                .findFirst()
                .orElseThrow(() -> new NoDatabaseFoundException(configuration));
        database.init(configuration);
        return database;
    }

    protected abstract void init(Configuration configuration) throws UnableToConnectException;

    protected abstract boolean isConfigured(Configuration configuration);

    protected abstract boolean canConnect(Configuration configuration);

    public abstract String getName();
}
