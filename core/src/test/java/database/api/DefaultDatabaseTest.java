package database.api;

import database.api.exception.UnableToConnectException;
import database.register.ParametersRegister;
import org.apache.commons.cli.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultDatabaseTest {
    @Test
    public void getsName() {
        try {
            Database database = Database.connect(parseArguments(new String[] { "-url", "jdbc:h2:mem:test", "-username", "test", "-password", "" }));
            assertEquals("H2", database.getName());
        } catch (UnableToConnectException e) {
            fail(e);
        }
    }

    private CommandLine parseArguments(String[] args) {
        Options options = new Options();
        for (Parameters parameters : ParametersRegister.getParameters()) {
            parameters.getAll().getOptions().forEach(options::addOption);
        }
        try {
            return new DefaultParser().parse(options, args);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
