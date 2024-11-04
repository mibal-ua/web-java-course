package ua.mibal.service.exception;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public abstract class NotFoundException extends RuntimeException {
    private final Class<?> clazz;

    protected NotFoundException(Class<?> clazz) {
        super(clazz.getSimpleName() + " not found");
        this.clazz = clazz;
    }

    @Override
    public String getMessage() {
        return clazz.getSimpleName() + " not found";
    }

    public String getType() {
        return clazz.getSimpleName().toLowerCase() + "-not-found";
    }

    public String getTitle() {
        return getMessage();
    }
}
