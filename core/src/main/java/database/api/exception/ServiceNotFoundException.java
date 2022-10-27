package database.api.exception;

public class ServiceNotFoundException extends RuntimeException {
    public ServiceNotFoundException(Class<?> clazz) {
        super("No service with class name '" + clazz.getSimpleName() + "' found");
    }
}
