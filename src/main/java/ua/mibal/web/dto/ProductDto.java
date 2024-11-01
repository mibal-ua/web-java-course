package ua.mibal.web.dto;

import lombok.Builder;
import ua.mibal.domain.Category;

import java.math.BigDecimal;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Builder
public record ProductDto(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Category category
) {
}
