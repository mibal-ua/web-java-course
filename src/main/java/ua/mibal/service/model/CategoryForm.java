package ua.mibal.service.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import ua.mibal.service.model.validation.constraint.CosmicWord;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Builder
public record CategoryForm(

        @NotBlank(message = "Name is mandatory")
        @Size(min = 5, message = "Name must be at least 5 characters long")
        @CosmicWord
        String name
) {
}
