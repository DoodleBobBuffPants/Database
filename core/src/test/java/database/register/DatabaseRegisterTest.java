package database.register;

import database.jdbc.JdbcDatabase;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseRegisterTest {
    @Test
    public void allDatabaseImplementationsAreLoaded() {
        List<String> databases = DatabaseRegister.get().stream().map(d -> d.getClass().getSimpleName()).toList();
        assertEquals(1, databases.size());
        assertTrue(databases.contains(JdbcDatabase.class.getSimpleName()), databases.toString());
    }
}
