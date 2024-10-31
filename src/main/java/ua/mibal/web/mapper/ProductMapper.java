package ua.mibal.web.mapper;

import ua.mibal.domain.Product;
import ua.mibal.web.dto.ProductDto;

import java.util.List;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public interface ProductMapper {
    List<ProductDto> toDto(List<Product> products);

    ProductDto toDto(Product product);
}
