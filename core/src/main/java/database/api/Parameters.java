package database.api;

import org.apache.commons.cli.Options;

public interface Parameters {
    Options getAll();
    boolean isConfigured(Options options);
}
