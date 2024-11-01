package ua.mibal.service.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import ua.mibal.service.model.validation.constraint.CosmicWord;

import java.math.BigDecimal;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Builder
public record ProductForm(

        @NotBlank(message = "Name is mandatory")
        @Size(min = 5, message = "Name must be at least 5 characters long")
        @CosmicWord
        String name,

        @CosmicWord
        String description,

        @NotNull(message = "Price is mandatory")
        @Min(value = 1, message = "Price must be at least 1")
        @Max(value = 1_000_000, message = "Price must be less than 100_000")
        BigDecimal price
) {
}
