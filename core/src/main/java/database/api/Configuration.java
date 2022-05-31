package database.api;

import database.api.exception.NoParameterFoundException;
import database.api.exception.UnableToConnectException;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Configuration {
    private final Map<Option, String> configuration = new HashMap<>();

    private Configuration() { }

    public Configuration set(Option key, String value) {
        configuration.put(key, value);
        return this;
    }

    public String get(Option key) {
       if (!configuration.containsKey(key)) {
           throw new NoParameterFoundException(key);
       }
       return configuration.get(key);
    }

    public Options toOptions() {
        Options options = new Options();
        configuration.keySet().forEach(options::addOption);
        return options;
    }

    public Database connect() throws UnableToConnectException {
        return Database.connect(this);
    }

    public static Configuration configure() {
        return new Configuration();
    }

    @Override
    public String toString() {
        return configuration.entrySet().stream()
                            .map(kv -> kv.getKey().getArgName() + " => " + kv.getValue())
                            .collect(Collectors.joining(System.lineSeparator()));
    }
}
