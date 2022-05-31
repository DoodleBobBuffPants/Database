package database.api.exception;

public class NoServiceFoundException extends RuntimeException {
    public NoServiceFoundException(Class<?> clazz) {
        super("No service with class name '" + clazz.getSimpleName() + "' found");
    }
}
