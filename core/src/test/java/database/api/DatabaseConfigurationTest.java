package database.api;

import database.api.exception.OptionNotFoundException;
import org.junit.jupiter.api.Test;

import static database.jdbc.JdbcDatabaseOptions.URL;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConfigurationTest {
    @Test
    public void failsOnGettingInvalidParameter() {
        OptionNotFoundException e = assertThrows(OptionNotFoundException.class, () -> DatabaseConfiguration.configure().get(URL));
        assertEquals("Option '" + URL.getOpt() + "' is not configured", e.getMessage());
    }
}
