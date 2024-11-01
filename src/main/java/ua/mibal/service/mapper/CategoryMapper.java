package ua.mibal.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ua.mibal.domain.Category;
import ua.mibal.service.model.CategoryForm;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Mapper(componentModel = SPRING)
public interface CategoryMapper {

    static CategoryMapper getInstance() {
        return Mappers.getMapper(CategoryMapper.class);
    }

    Category toEntity(CategoryForm product);

    Category toEntity(Long id, CategoryForm form);

    void update(@MappingTarget Category target, CategoryForm source);
}
