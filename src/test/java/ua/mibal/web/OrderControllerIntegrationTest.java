package ua.mibal.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ua.mibal.repository.CategoryRepository;
import ua.mibal.repository.OrderRepository;
import ua.mibal.repository.ProductRepository;
import ua.mibal.repository.entity.CategoryEntity;
import ua.mibal.repository.entity.OrderEntity;
import ua.mibal.repository.entity.ProductEntity;

import java.util.Date;
import java.util.Set;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
class OrderControllerIntegrationTest extends IntegrationTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void clean() {
        orderRepository.deleteAll();
        productRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void getAll() throws Exception {
        CategoryEntity spaceship = given(CategoryEntity.builder()
                .name("SPACESHIP")
                .build());
        ProductEntity r2d2sSpaceship = given(ProductEntity.builder()
                .name("R2D2's Spaceship")
                .description("The best spaceship in the galaxy")
                .price(valueOf(100500))
                .categories(Set.of(spaceship))
                .build());
        given(OrderEntity.builder()
                .product(r2d2sSpaceship)
                .quantity(3)
                .timestamp(new Date())
                .build());

        mvc.perform(get("/api/v1/order/orders"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                          {
                            "product": {
                              "name": "R2D2's Spaceship",
                              "description": "The best spaceship in the galaxy",
                              "price": 100500,
                              "categories": [
                                {
                                  "name": "SPACESHIP"
                                }
                              ]
                            },
                            "quantity": 3
                          }
                        ]
                        """));
    }

    @Test
    void getOne() throws Exception {
        CategoryEntity spaceship = given(CategoryEntity.builder()
                .name("SPACESHIP")
                .build());
        ProductEntity r2d2sSpaceship = given(ProductEntity.builder()
                .name("R2D2's Spaceship")
                .description("The best spaceship in the galaxy")
                .price(valueOf(100500))
                .categories(Set.of(spaceship))
                .build());
        OrderEntity order = given(OrderEntity.builder()
                .product(r2d2sSpaceship)
                .quantity(3)
                .timestamp(new Date())
                .build());

        mvc.perform(get("/api/v1/order/orders/{id}", order.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "product": {
                              "name": "R2D2's Spaceship",
                              "description": "The best spaceship in the galaxy",
                              "price": 100500,
                              "categories": [
                                {
                                  "name": "SPACESHIP"
                                }
                              ]
                            },
                            "quantity": 3
                          }
                        """));
    }

    @Test
    void create() throws Exception {
        CategoryEntity spaceship = given(CategoryEntity.builder()
                .name("SPACESHIP")
                .build());
        ProductEntity r2d2sSpaceship = given(ProductEntity.builder()
                .name("R2D2's Spaceship")
                .description("The best spaceship in the galaxy")
                .price(valueOf(100500))
                .categories(Set.of(spaceship))
                .build());

        mvc.perform(post("/api/v1/order/orders")
                        .contentType("application/json")
                        .content(String.format(
                                """
                                        {
                                            "productId": %d,
                                            "quantity": 3
                                        }
                                        """, r2d2sSpaceship.getId()
                        )))
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                        {
                            "product": {
                              "name": "R2D2's Spaceship",
                              "description": "The best spaceship in the galaxy",
                              "price": 100500,
                              "categories": [
                                {
                                  "name": "SPACESHIP"
                                }
                              ]
                            },
                            "quantity": 3
                          }
                        """));
    }

    @Test
    void update() throws Exception {
        CategoryEntity spaceship = given(CategoryEntity.builder()
                .name("SPACESHIP")
                .build());
        ProductEntity r2d2sSpaceship = given(ProductEntity.builder()
                .name("R2D2's Spaceship")
                .description("The best spaceship in the galaxy")
                .price(valueOf(100500))
                .categories(Set.of(spaceship))
                .build());
        ProductEntity upgradedR2d2sSpaceship = given(ProductEntity.builder()
                .name("R2D2's Upgraded Spaceship")
                .description("The best twice spaceship in the galaxy")
                .price(valueOf(200600))
                .categories(Set.of(spaceship))
                .build());
        OrderEntity order = given(OrderEntity.builder()
                .product(r2d2sSpaceship)
                .quantity(3)
                .timestamp(new Date())
                .build());

        mvc.perform(put("/api/v1/order/orders/{id}", order.getId())
                        .contentType("application/json")
                        .content(String.format(
                                """
                                        {
                                            "productId": %d,
                                            "quantity": 8
                                        }
                                        """, upgradedR2d2sSpaceship.getId()
                        )))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                            "product": {
                              "name": "R2D2's Upgraded Spaceship",
                              "description": "The best twice spaceship in the galaxy",
                              "price": 200600,
                              "categories": [
                                {
                                  "name": "SPACESHIP"
                                }
                              ]
                            },
                            "quantity": 8
                          }
                        """));
    }

    @Test
    void delete() throws Exception {
        CategoryEntity spaceship = given(CategoryEntity.builder()
                .name("SPACESHIP")
                .build());
        ProductEntity r2d2sSpaceship = given(ProductEntity.builder()
                .name("R2D2's Spaceship")
                .description("The best spaceship in the galaxy")
                .price(valueOf(100500))
                .categories(Set.of(spaceship))
                .build());
        OrderEntity order = given(OrderEntity.builder()
                .product(r2d2sSpaceship)
                .quantity(3)
                .timestamp(new Date())
                .build());

        mvc.perform(MockMvcRequestBuilders.delete("/api/v1/order/orders/{id}", order.getId()))
                .andExpect(status().isNoContent());

        assertThat(orderRepository.existsById(order.getId())).isFalse();
    }

    @Test
    void getProductOrderingStatistics() throws Exception {
        CategoryEntity spaceship = given(CategoryEntity.builder()
                .name("SPACESHIP")
                .build());

        ProductEntity r2d2sSpaceship = given(ProductEntity.builder()
                .name("R2D2's Spaceship")
                .description("The best spaceship in the galaxy")
                .price(valueOf(100500))
                .categories(Set.of(spaceship))
                .build());
        given(OrderEntity.builder()
                .product(r2d2sSpaceship)
                .quantity(3)
                .timestamp(new Date())
                .build());
        given(OrderEntity.builder()
                .product(r2d2sSpaceship)
                .quantity(7)
                .timestamp(new Date())
                .build());

        ProductEntity woodenSpaceship = given(ProductEntity.builder()
                .name("Wooden Spaceship")
                .description("The worst spaceship in the whole galaxy")
                .price(valueOf(-100))
                .categories(Set.of(spaceship))
                .build());

        mvc.perform(get("/api/v1/order/orders/statistics"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                            {
                                "productName": "R2D2's Spaceship",
                                "orderingCount": 10
                            },
                            {
                                "productName": "Wooden Spaceship",
                                "orderingCount": 0
                            }
                        ]
                        """));
    }

    private CategoryEntity given(CategoryEntity category) {
        return categoryRepository.save(category);
    }


    private OrderEntity given(OrderEntity order) {
        return orderRepository.save(order);
    }

    private ProductEntity given(ProductEntity product) {
        return productRepository.save(product);
    }
}
