package ua.mibal.test.featureToggle.annotation;

import ua.mibal.featureToggle.model.ToggleableFeature;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface EnableFeature {
    
    ToggleableFeature value();
}
