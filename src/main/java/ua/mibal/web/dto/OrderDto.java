package ua.mibal.web.dto;

import java.util.Date;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public record OrderDto(
        ProductDto product,
        Integer quantity,
        Date timestamp
) {
}
