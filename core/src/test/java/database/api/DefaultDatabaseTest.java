package database.api;

import database.api.exception.NoDatabaseFoundException;
import database.api.exception.UnableToConnectException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultDatabaseTest {
    @Test
    public void getsName() {
        try {
            Database database = getDefaultConfiguration().connect();
            assertEquals("H2", database.getName());
        } catch (UnableToConnectException e) {
            fail(e);
        }
    }

    @Test
    public void failsOnEmptyConfiguration() {
        NoDatabaseFoundException e = assertThrows(NoDatabaseFoundException.class, () -> Configuration.configure().connect());
        assertEquals("No database found for the current configuration:" + System.lineSeparator(), e.getMessage());
    }

    private Configuration getDefaultConfiguration() {
        return Configuration.configure()
                            .set(DefaultParameters.url, "jdbc:h2:mem:test")
                            .set(DefaultParameters.username, "-username")
                            .set(DefaultParameters.password, "");
    }
}
