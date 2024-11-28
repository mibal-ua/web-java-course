package ua.mibal.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ua.mibal.domain.Category;
import ua.mibal.repository.entity.CategoryEntity;
import ua.mibal.service.model.CategoryForm;

import java.util.List;

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

    CategoryEntity toEntity(CategoryForm product);

    CategoryEntity toEntity(String name, CategoryForm form);

    void update(@MappingTarget CategoryEntity target, CategoryForm source);

    List<Category> toModel(List<CategoryEntity> all);

    Category toModel(CategoryEntity categoryEntity);
}
