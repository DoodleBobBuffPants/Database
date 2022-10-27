package database.api;

import database.api.exception.NoDatabaseFoundException;
import database.register.DatabaseRegister;

public abstract class Database {
    static Database connect(DatabaseConfiguration configuration) {
        Database database = DatabaseRegister.get().stream()
                                            .filter(d -> d.isConfigured(configuration))
                                            .filter(d -> d.canConnect(configuration))
                                            .findFirst()
                                            .orElseThrow(() -> new NoDatabaseFoundException(configuration));
        database.init(configuration);
        return database;
    }

    protected abstract boolean isConfigured(DatabaseConfiguration configuration);

    protected abstract boolean canConnect(DatabaseConfiguration configuration);

    protected abstract void init(DatabaseConfiguration configuration);

    public abstract String getName();
}
