package ua.mibal.service.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Builder
public record OrderForm(

        @Min(1)
        @Max(100)
        int quantity
) {
}
