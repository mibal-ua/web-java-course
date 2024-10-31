package ua.mibal.service.model;

import lombok.Builder;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Builder
public record ProductForm(
        String name,
        String description,
        Double price
) {
}
