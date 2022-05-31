package database.api;

import database.api.exception.NoParameterFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigurationTest {
    @Test
    public void failsOnGettingInvalidParameter()
    {
        NoParameterFoundException e = assertThrows(NoParameterFoundException.class, () -> Configuration.configure().get(DefaultParameters.url));
        assertEquals("Parameter '" + DefaultParameters.url.getArgName() + "' is not configured", e.getMessage());
    }
}
