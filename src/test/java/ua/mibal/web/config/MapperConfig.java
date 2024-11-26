package ua.mibal.web.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ua.mibal.web.mapper.CategoryDtoMapper;
import ua.mibal.web.mapper.ProductDtoMapper;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@TestConfiguration
public class MapperConfig {

    @Bean
    public CategoryDtoMapper categoryDtoMapper() {
        return CategoryDtoMapper.getInstance();
    }

    @Bean
    public ProductDtoMapper productDtoMapper() {
        return ProductDtoMapper.getInstance();
    }
}
