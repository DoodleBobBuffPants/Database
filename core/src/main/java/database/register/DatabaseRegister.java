package database.register;

import database.api.Database;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class DatabaseRegister {
    private static List<Database> databases = null;

    public static List<Database> getDatabases() {
        if (databases == null) {
            register();
        }
        return databases;
    }

    private synchronized static void register() {
        databases = new ArrayList<>();
        ServiceLoader.load(Database.class).forEach(t -> databases.add(t));
    }
}
