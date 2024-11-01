package ua.mibal.service.mapper;

import org.junit.jupiter.api.Test;
import ua.mibal.domain.Category;
import ua.mibal.service.model.CategoryForm;
import ua.mibal.test.annotation.UnitTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyNoInteractions;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@UnitTest
class CategoryMapperTest {
    private final CategoryMapper mapper = CategoryMapper.getInstance();

    @Test
    void toEntity_shouldReturnNull() {
        Category actual = mapper.toEntity(null);

        assertNull(actual);
    }

    @Test
    void toEntity_withId_shouldReturnNull() {
        assertNull(mapper.toEntity(null, null));
    }

    @Test
    void toEntity_withId_shouldMapIfFormIsNull() {
        Category actual = mapper.toEntity(1L, null);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getName()).isNull();
    }

    @Test
    void toEntity_withId_shouldMapIfIdIsNull() {
        CategoryForm form = CategoryForm.builder()
                .name("name")
                .build();

        Category actual = mapper.toEntity(null, form);

        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isNull();
        assertThat(actual.getName()).isEqualTo("name");
    }

    @Test
    void update_shouldNotUpdateIfSourceIsNull() {
        Category target = mock();

        mapper.update(target, null);

        verifyNoInteractions(target);
    }
}
