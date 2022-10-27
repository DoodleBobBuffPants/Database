package database.api;

import database.api.exception.OptionNotFoundException;
import org.apache.commons.cli.Option;

import java.util.HashMap;
import java.util.Map;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;

public class DatabaseConfiguration {
    private final Map<Option, String> configuration = new HashMap<>();

    private DatabaseConfiguration() { }

    public static DatabaseConfiguration configure() {
        return new DatabaseConfiguration();
    }

    public DatabaseConfiguration set(Option key, String value) {
        configuration.put(key, value);
        return this;
    }

    public String get(Option key) {
        if (!exists(key)) throw new OptionNotFoundException(key);
        return configuration.get(key);
    }

    public boolean exists(Option key) {
        return configuration.containsKey(key);
    }

    public Database connect() {
        return Database.connect(this);
    }

    @Override
    public String toString() {
        return configuration.entrySet().stream()
                            .map(kv -> kv.getKey().getOpt() + " => " + kv.getValue())
                            .collect(joining(lineSeparator()));
    }
}
