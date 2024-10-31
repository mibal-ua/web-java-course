package ua.mibal.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.mibal.domain.Product;
import ua.mibal.service.ProductService;
import ua.mibal.service.exception.ConflictException;
import ua.mibal.service.exception.ProductNotFoundException;
import ua.mibal.service.model.ProductForm;

import java.util.List;
import java.util.stream.Stream;

import static java.math.BigDecimal.valueOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@WebMvcTest(ProductController.class)
class ProductControllerTest extends ControllerTest {
    @MockBean
    private ProductService productService;
    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        reset(productService);
    }

    @Test
    void getAll() throws Exception {
        given(Product.builder()
                .id(101L)
                .name("Space milk")
                .description("Milk from the space cow")
                .price(valueOf(100))
                .build());

        mvc.perform(get("/v1/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                          {
                            "name": "Space milk",
                            "description": "Milk from the space cow",
                            "price": 100
                          }
                        ]
                        """, true));
    }

    @Test
    void getOne() throws Exception {
        given(Product.builder()
                .id(101L)
                .name("Space milk")
                .description("Milk from the space cow")
                .price(valueOf(100))
                .build());

        mvc.perform(get("/v1/api/products/101"))
                .andExpect(status().isOk())
                .andExpect(content().json("""  
                        {
                          "name": "Space milk",
                          "description": "Milk from the space cow",
                          "price": 100
                        }
                        """, true));
    }

    @Test
    void getOne_shouldReturnNotFound() throws Exception {
        givenEmptyService();

        mvc.perform(get("/v1/api/products/101"))
                .andExpect(status().isNotFound());
    }

    @Test
    void create() throws Exception {
        givenEmptyService();

        mvc.perform(post("/v1/api/products")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Space milk",
                                  "description": "Milk from the space cow",
                                  "price": 100
                                }
                                """))
                .andExpect(status().isCreated());
    }

    @Test
    void create_shouldReturnConflictIfNameIsNotUnique() throws Exception {
        givenServiceThatThrowsConflictExceptionOnName("Same name");

        mvc.perform(post("/v1/api/products")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Same name",
                                  "description": "Another description",
                                  "price": 100
                                }
                                """))
                .andExpect(status().isConflict());
    }

    @ParameterizedTest
    @MethodSource("ua.mibal.web.ProductControllerTest#invalidProductForms")
    void create_shouldReturnBadRequestIfFieldsAreNotValid(ProductForm form) throws Exception {
        givenEmptyService();

        mvc.perform(post("/v1/api/products")
                        .contentType(APPLICATION_JSON)
                        .content(mapper.writeValueAsString(form)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update() {
    }

    @Test
    void delete() throws Exception {
        given(Product.builder()
                .id(101L)
                .name("Space milk")
                .description("Milk from the space cow")
                .price(valueOf(100))
                .build());

        mvc.perform(MockMvcRequestBuilders.delete("/v1/api/products/101"))
                .andExpect(status().isNoContent());
    }

    @Test
    void delete_shouldNotThrowExceptionEvenIfNotFound() throws Exception {
        givenEmptyService();

        mvc.perform(MockMvcRequestBuilders.delete("/v1/api/products/101"))
                .andExpect(status().isNoContent());
    }

    private static Stream<ProductForm> invalidProductForms() {
        return Stream.of(
                // name
                valid().name(null),
                valid().name(""),
                valid().name("litl"),
                valid().name("         "),
                // price
                valid().price(valueOf(-1)),
                valid().price(valueOf(0)),
                valid().price(valueOf(0.99)),
                valid().price(valueOf(100_000_001))
        ).map(ProductForm.ProductFormBuilder::build);
    }

    private static ProductForm.ProductFormBuilder valid() {
        return ProductForm.builder()
                .name("Valid name")
                .description("Valid description")
                .price(valueOf(100));
    }

    private void givenServiceThatThrowsConflictExceptionOnName(String name) {
        when(productService.create(any()))
                .thenAnswer(invocation -> {
                    ProductForm form = invocation.getArgument(0);
                    if (name.equals(form.name())) {
                        throw new ConflictException("");
                    }
                    return null;
                });
        when(productService.update(any(), any()))
                .thenAnswer(invocation -> {
                    ProductForm form = invocation.getArgument(1);
                    if (name.equals(form.name())) {
                        throw new ConflictException("");
                    }
                    return null;
                });
    }

    private void givenEmptyService() {
        when(productService.getAll())
                .thenReturn(List.of());
        when(productService.getOneById(any()))
                .thenThrow(new ProductNotFoundException());
        doThrow(new ProductNotFoundException())
                .when(productService)
                .deleteById(any());
    }

    private void given(Product... products) {
        when(productService.getAll())
                .thenReturn(List.of(products));

        for (Product product : products) {
            when(productService.getOneById(product.getId()))
                    .thenReturn(product);
        }
    }
}
