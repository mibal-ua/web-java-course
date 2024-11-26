package ua.mibal.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ua.mibal.domain.Product;
import ua.mibal.service.model.ProductForm;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Mapper(componentModel = SPRING)
public interface ProductMapper {

    static ProductMapper getInstance() {
        return Mappers.getMapper(ProductMapper.class);
    }

    Product toEntity(ProductForm product);

    Product toEntity(Long id, ProductForm form);

    void update(@MappingTarget Product product, ProductForm form);

    ProductForm toForm(Product product);
}
