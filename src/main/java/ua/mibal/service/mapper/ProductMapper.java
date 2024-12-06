package ua.mibal.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ua.mibal.domain.Product;
import ua.mibal.repository.entity.ProductEntity;
import ua.mibal.service.model.ProductForm;

import java.util.List;

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

    ProductEntity toEntity(ProductForm product);

    ProductEntity toEntity(Long id, ProductForm form);

    void update(@MappingTarget ProductEntity product, ProductForm form);

    ProductForm toForm(Product product);

    Product toModel(ProductEntity productEntity);

    List<Product> toModel(List<ProductEntity> productEntities);
}
