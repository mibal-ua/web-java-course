package ua.mibal.web.dto;

import java.math.BigDecimal;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public record ProductDto(
        String name,
        String description,
        BigDecimal price
) {
}
