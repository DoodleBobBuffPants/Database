package database.register;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParametersRegisterTest {
    @Test
    public void allParametersImplementationsAreLoaded() {
        List<String> parameters = ParametersRegister.getParameters().stream().map(p -> p.getClass().getSimpleName()).toList();
        assertEquals(1, parameters.size());
    }
}
