package ua.mibal.service.mapper;

import org.junit.jupiter.api.Test;
import ua.mibal.domain.Product;
import ua.mibal.service.model.ProductForm;
import ua.mibal.test.annotation.UnitTest;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@UnitTest
class ProductMapperTest {
    private final ProductMapper mapper = ProductMapper.getInstance();

    @Test
    void toEntity_shouldReturnNull() {
        Product actual = mapper.toEntity(null);

        assertNull(actual);
    }

    @Test
    void toEntity_withId_shouldReturnNull() {
        assertNull(mapper.toEntity(null, null));
    }

    @Test
    void toEntity_withId_shouldMapIfFormIsNull() {
        Product actual = mapper.toEntity(1L, null);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getName()).isNull();
        assertThat(actual.getDescription()).isNull();
        assertThat(actual.getPrice()).isNull();
    }

    @Test
    void toEntity_withId_shouldMapIfIdIsNull() {
        ProductForm form = ProductForm.builder()
                .name("name")
                .description("description")
                .price(valueOf(1.0))
                .build();

        Product actual = mapper.toEntity(null, form);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNull();
        assertThat(actual.getName()).isEqualTo("name");
        assertThat(actual.getDescription()).isEqualTo("description");
        assertThat(actual.getPrice()).isEqualTo(valueOf(1.0));
    }

    @Test
    void update_shouldNotUpdateIfSourceIsNull() {
        Product target = mock();

        mapper.update(target, null);

        verifyNoInteractions(target);
    }
}
