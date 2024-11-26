package ua.mibal.web.mapper;

import org.junit.jupiter.api.Test;
import ua.mibal.domain.Product;
import ua.mibal.test.annotation.UnitTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@UnitTest
class ProductDtoMapperTest {
    private final ProductDtoMapper mapper = ProductDtoMapper.getInstance();

    @Test
    void toDto_shouldReturnNull() {
        assertNull(mapper.toDto((Product) null));
        assertNull(mapper.toDto((List<Product>) null));
    }
}
