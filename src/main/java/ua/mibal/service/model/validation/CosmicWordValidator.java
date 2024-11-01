package ua.mibal.service.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ua.mibal.service.model.validation.constraint.CosmicWord;

import java.util.List;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class CosmicWordValidator implements ConstraintValidator<CosmicWord, String> {
    private static final List<String> COSMIC_WORDS = List.of(
            "cosmic", "space", "universe", "planet", "star", "galaxy", "moon", "sun", "earth"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null
               || COSMIC_WORDS.stream()
                       .anyMatch(word -> value.toLowerCase().contains(word));
    }
}
