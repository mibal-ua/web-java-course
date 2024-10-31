package ua.mibal.web.mapper;

import org.mapstruct.Mapper;
import ua.mibal.domain.Product;
import ua.mibal.web.dto.ProductDto;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Mapper(componentModel = SPRING)
public interface ProductMapper {

    List<ProductDto> toDto(List<Product> products);

    ProductDto toDto(Product product);
}
