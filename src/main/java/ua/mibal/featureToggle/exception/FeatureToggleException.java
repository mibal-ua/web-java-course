package ua.mibal.featureToggle.exception;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class FeatureToggleException extends RuntimeException {

    public FeatureToggleException(String message) {
        super(message);
    }

    public FeatureToggleException(String message, Throwable cause) {
        super(message, cause);
    }
}
