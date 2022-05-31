package database.register;

import database.api.Parameters;
import database.api.exception.NoServiceFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class ParametersRegister {
    private static List<Parameters> parameters = null;

    public static List<Parameters> getParameters() {
        if (parameters == null) {
            register();
        }
        return parameters;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Parameters> T getParameter(Class<T> clazz) {
        return (T) getParameters().stream()
                                  .filter(p -> p.getClass().getCanonicalName().equals(clazz.getCanonicalName()))
                                  .findFirst()
                                  .orElseThrow(() -> new NoServiceFoundException(clazz));
    }

    private synchronized static void register() {
        parameters = new ArrayList<>();
        ServiceLoader.load(Parameters.class).forEach(t -> parameters.add(t));
    }
}
