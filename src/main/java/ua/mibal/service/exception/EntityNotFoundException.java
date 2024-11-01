package ua.mibal.service.exception;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public abstract class EntityNotFoundException extends RuntimeException {
    private final Class<?> entityClass;

    protected EntityNotFoundException(Class<?> entityClass) {
        super(entityClass.getSimpleName() + " not found");
        this.entityClass = entityClass;
    }

    @Override
    public String getMessage() {
        return entityClass.getSimpleName() + " not found";
    }

    public String getType() {
        return entityClass.getSimpleName().toLowerCase() + "-not-found";
    }

    public String getTitle() {
        return getMessage();
    }
}
