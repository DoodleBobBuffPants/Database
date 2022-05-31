package database;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    @Test
    public void successfullyParsesArgs() throws ParseException {
        assertEquals(3, Main.parseArguments(new String[] { "-url", "jdbc:h2:mem:test", "-username", "test", "-password", "" }).getOptions().length);
    }

    @Test
    public void successfullyPrintsHelp() throws Exception {
        Main.main(new String[] { });
    }
}
