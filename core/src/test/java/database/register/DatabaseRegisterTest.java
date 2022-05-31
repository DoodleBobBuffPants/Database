package database.register;

import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseRegisterTest {
    @Test
    public void allDatabaseImplementationsAreLoaded() {
        List<String> databases = DatabaseRegister.getDatabases().stream().map(p -> p.getClass().getSimpleName()).toList();
        assertEquals(1, databases.size());
    }
}
