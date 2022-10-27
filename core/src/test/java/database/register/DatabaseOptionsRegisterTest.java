package database.register;

import database.cli.CliDatabaseOptions;
import database.jdbc.JdbcDatabaseOptions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseOptionsRegisterTest {
    @Test
    public void allDatabaseOptionsImplementationsAreLoaded() {
        List<String> optionsClasses = DatabaseOptionsRegister.get().stream().map(o -> o.getClass().getSimpleName()).toList();
        assertEquals(2, optionsClasses.size());
        assertTrue(optionsClasses.contains(JdbcDatabaseOptions.class.getSimpleName()), optionsClasses.toString());
        assertTrue(optionsClasses.contains(CliDatabaseOptions.class.getSimpleName()), optionsClasses.toString());
    }
}
