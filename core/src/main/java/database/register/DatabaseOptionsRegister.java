package database.register;

import database.api.DatabaseOptions;
import database.api.exception.ServiceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class DatabaseOptionsRegister {
    private static List<DatabaseOptions> options = null;

    @SuppressWarnings("unchecked")
    public static <T extends DatabaseOptions> T get(Class<T> clazz) {
        return (T) get().stream()
                        .filter(o -> o.getClass().getCanonicalName().equals(clazz.getCanonicalName()))
                        .findFirst()
                        .orElseThrow(() -> new ServiceNotFoundException(clazz));
    }

    public static List<DatabaseOptions> get() {
        if (options == null) register();
        return options;
    }

    private synchronized static void register() {
        options = new ArrayList<>();
        ServiceLoader.load(DatabaseOptions.class).forEach(o -> options.add(o));
    }
}
