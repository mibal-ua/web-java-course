package ua.mibal.web.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ua.mibal.domain.Category;
import ua.mibal.web.dto.CategoryDto;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Mapper(componentModel = SPRING)
public interface CategoryDtoMapper {

    static CategoryDtoMapper getInstance() {
        return Mappers.getMapper(CategoryDtoMapper.class);
    }

    List<CategoryDto> toDto(List<Category> categories);

    CategoryDto toDto(Category category);
}
