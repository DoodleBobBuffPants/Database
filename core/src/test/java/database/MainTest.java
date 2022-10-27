package database;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static database.Main.*;
import static database.jdbc.JdbcDatabaseOptions.*;
import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    @Test
    public void successfullyParsesArgs() throws ParseException {
        CommandLine cmd = parseArguments(new String[] { "-url", "jdbc:h2:mem:test", "-username", "test", "-password", "test" });
        assertEquals(3, cmd.getOptions().length);
        assertTrue(cmd.hasOption(URL), toString(cmd));
        assertTrue(cmd.hasOption(USERNAME), toString(cmd));
        assertTrue(cmd.hasOption(PASSWORD), toString(cmd));
    }

    private String toString(CommandLine cmd) {
        return Arrays.stream(cmd.getOptions())
                     .map(o -> o.getOpt() + " => " + cmd.getOptionValue(o))
                     .collect(joining(", "));
    }

    @Test
    public void successfullyPrintsHelp() throws Exception {
        main(new String[] { "-help" });
    }
}
