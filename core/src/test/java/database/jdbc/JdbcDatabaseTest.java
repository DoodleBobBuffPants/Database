package database.jdbc;

import database.api.Database;
import database.api.DatabaseConfiguration;
import database.api.exception.NoDatabaseFoundException;
import database.api.exception.UnableToConnectException;
import org.junit.jupiter.api.Test;

import static database.jdbc.JdbcDatabaseOptions.*;
import static org.junit.jupiter.api.Assertions.*;

public class JdbcDatabaseTest {
    @Test
    public void getsName() {
        try {
            Database database = getDefaultConfiguration().connect();
            assertEquals("H2", database.getName());
        } catch (UnableToConnectException e) {
            fail(e);
        }
    }

    private DatabaseConfiguration getDefaultConfiguration() {
        return DatabaseConfiguration.configure().set(URL, "jdbc:h2:mem:test").set(USERNAME, "test").set(PASSWORD, "test");
    }

    @Test
    public void failsOnEmptyConfiguration() {
        NoDatabaseFoundException e = assertThrows(NoDatabaseFoundException.class, () -> DatabaseConfiguration.configure().connect());
        assertEquals("No database found for the current configuration:" + System.lineSeparator(), e.getMessage());
    }
}
