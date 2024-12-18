package ua.mibal.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.mibal.repository.CategoryRepository;
import ua.mibal.repository.entity.CategoryEntity;

import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@WithMockUser
class CategoryControllerIntegrationTest extends IntegrationTest {
    @Autowired
    private CategoryRepository repository;

    @BeforeEach
    void clean() {
        repository.deleteAll();
    }

    @Test
    void getAll() throws Exception {
        given(CategoryEntity.builder()
                .name("SPACESHIP")
                .build());

        mvc.perform(get("/api/v1/order/categories"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                          {
                            "name": "SPACESHIP"
                          }
                        ]
                        """));
    }

    @Test
    void getOne() throws Exception {
        given(CategoryEntity.builder()
                .name("SPACESHIP")
                .build());

        mvc.perform(get("/api/v1/order/categories/SPACESHIP"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                          "name": "SPACESHIP"
                        }
                        """));
    }

    @Test
    void getOne_NotFound() throws Exception {
        mvc.perform(get("/api/v1/order/categories/SPACESHIP"))
                .andExpect(status().isNotFound());
    }

    @Test
    void create() throws Exception {
        mvc.perform(post("/api/v1/order/categories")
                        .contentType("application/json")
                        .content("""
                                {
                                  "name": "SPACESHIP"
                                }
                                """))
                .andExpect(status().isCreated());

        verifyExists(CategoryEntity.builder()
                .name("SPACESHIP")
                .build());
    }

    @Test
    void create_shouldThrow_ifAlreadyExists() throws Exception {
        given(CategoryEntity.builder()
                .name("SPACESHIP")
                .build());

        mvc.perform(post("/api/v1/order/categories")
                        .contentType("application/json")
                        .content("""
                                {
                                  "name": "SPACESHIP"
                                }
                                """))
                .andExpect(status().isConflict());
    }

    @Test
    void delete() throws Exception {
        given(CategoryEntity.builder()
                .name("SPACESHIP")
                .build());

        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/order/categories/SPACESHIP"))
                .andExpect(status().isNoContent());

        verifyDoesNotExist(CategoryEntity.builder()
                .name("SPACESHIP")
                .build());
    }

    private void given(CategoryEntity... category) {
        repository.saveAll(asList(category));
    }

    private void verifyExists(CategoryEntity category) {
        assertThat(
                repository.existsByNaturalId(category.getName())
        ).isTrue();
    }

    private void verifyDoesNotExist(CategoryEntity category) {
        assertThat(
                repository.existsByNaturalId(category.getName())
        ).isFalse();
    }
}
