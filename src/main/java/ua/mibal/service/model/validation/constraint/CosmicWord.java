package ua.mibal.service.model.validation.constraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ua.mibal.service.model.validation.CosmicWordValidator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 
 * This annotation is used to validate the presence of cosmic words in the {@link String} field.
 * {@code null} elements are considered valid.
 * To see the list of cosmic words, please refer to {@link CosmicWordValidator#COSMIC_WORDS}
 * 
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = CosmicWordValidator.class)
public @interface CosmicWord {

    String message() default "Field must contain at least one cosmic word";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
