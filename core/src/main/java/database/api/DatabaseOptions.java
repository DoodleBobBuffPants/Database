package database.api;

import org.apache.commons.cli.Options;

public abstract class DatabaseOptions {
    public boolean isConfigured(DatabaseConfiguration configuration) {
        return getRequired().getOptions().stream().allMatch(configuration::exists);
    }

    public abstract Options getRequired();

    public abstract Options getAll();
}
