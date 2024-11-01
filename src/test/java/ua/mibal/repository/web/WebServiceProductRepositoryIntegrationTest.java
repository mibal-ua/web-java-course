package ua.mibal.repository.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.matching.ContentPattern;
import com.github.tomakehurst.wiremock.matching.MatchesJsonSchemaPattern;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.mibal.domain.Product;
import ua.mibal.service.model.ProductForm;
import ua.mibal.web.dto.ProductDto;

import java.util.List;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
class WebServiceProductRepositoryIntegrationTest extends WireMockTest {
    @Autowired
    private WebServiceProductRepository repository;

    @Test
    void existsByName() {
        stubFor(WireMock.get("/v1/api/products/exists?name=Space%20milk")
                .willReturn(aResponse().withStatus(204)));


        assertThat(repository.existsByName("Space milk")).isTrue();
    }

    @Test
    void existsByName_shouldReturnFalseIfNotFound() {
        stubFor(WireMock.get("/v1/api/products/exists?name=Space%20milk")
                .willReturn(aResponse().withStatus(404)));

        assertThat(repository.existsByName("Space milk")).isFalse();
    }

    @Test
    void findById() {
        ProductDto spaceMilk = ProductDto.builder()
                .id(101L)
                .name("Space milk")
                .description("Super duper Space milk")
                .price(valueOf(100))
                .build();

        stubFor(WireMock.get("/v1/api/products/101")
                .willReturn(aResponse().withStatus(200)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .withBody(json(spaceMilk))));

        Optional<Product> actual = repository.findById(101L);

        assertThat(actual).isPresent();
        assertThat(actual.get().getId()).isEqualTo(101L);
        assertThat(actual.get().getName()).isEqualTo("Space milk");
        assertThat(actual.get().getDescription()).isEqualTo("Super duper Space milk");
        assertThat(actual.get().getPrice()).isEqualTo(valueOf(100));
    }

    @Test
    void findById_shouldReturnEmptyIfNotFound() {
        stubFor(WireMock.get("/v1/api/products/101")
                .willReturn(aResponse().withStatus(404)));

        Optional<Product> actual = repository.findById(101L);

        assertThat(actual).isEmpty();
    }

    @Test
    void findAll() {
        stubFor(WireMock.get("/v1/api/products")
                .willReturn(aResponse().withStatus(200)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .withBody(json(List.of(
                                ProductDto.builder()
                                        .id(101L)
                                        .name("Space milk1")
                                        .description("Super duper Space milk1")
                                        .price(valueOf(100))
                                        .build(),
                                ProductDto.builder()
                                        .id(202L)
                                        .name("Space milk2")
                                        .description("Super duper Space milk2")
                                        .price(valueOf(200))
                                        .build()
                        )))));

        List<Product> actual = repository.findAll();

        assertThat(actual).hasSize(2);
        assertThat(actual.get(0).getId()).isEqualTo(101L);
        assertThat(actual.get(0).getName()).isEqualTo("Space milk1");
        assertThat(actual.get(0).getDescription()).isEqualTo("Super duper Space milk1");
        assertThat(actual.get(0).getPrice()).isEqualTo(valueOf(100));
    }

    @Test
    void save() {
        Product product = Product.builder()
                .name("Space milk")
                .description("Super duper Space milk")
                .price(valueOf(100))
                .build();
        ProductForm form = ProductForm.builder()
                .name("Space milk")
                .description("Super duper Space milk")
                .price(valueOf(100))
                .build();

        stubFor(WireMock.post("/v1/api/products")
                .withRequestBody(jsonRequest(form))
                .willReturn(aResponse().withStatus(200)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .withBody(json(
                                ProductDto.builder()
                                        .id(101L)
                                        .name("Space milk")
                                        .description("Super duper Space milk")
                                        .price(valueOf(100))
                                        .build())
                        )));

        Product actual = repository.save(product);

        assertThat(actual.getId()).isEqualTo(101L);
        assertThat(actual.getName()).isEqualTo("Space milk");
        assertThat(actual.getDescription()).isEqualTo("Super duper Space milk");
        assertThat(actual.getPrice()).isEqualTo(valueOf(100));
    }

    @Test
    void save_ifUpdatingExistingProduct() {
        Product product = Product.builder()
                .id(101L)
                .name("Space milk")
                .description("Super duper Space milk")
                .price(valueOf(100))
                .build();
        ProductForm form = ProductForm.builder()
                .name("Space milk")
                .description("Super duper Space milk")
                .price(valueOf(100))
                .build();

        stubFor(WireMock.put("/v1/api/products/101")
                .withRequestBody(jsonRequest(form))
                .willReturn(aResponse().withStatus(200)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .withBody(json(
                                ProductDto.builder()
                                        .id(101L)
                                        .name("Space milk")
                                        .description("Super duper Space milk")
                                        .price(valueOf(100))
                                        .build())
                        )));

        Product actual = repository.save(product);

        assertThat(actual.getId()).isEqualTo(101L);
        assertThat(actual.getName()).isEqualTo("Space milk");
        assertThat(actual.getDescription()).isEqualTo("Super duper Space milk");
        assertThat(actual.getPrice()).isEqualTo(valueOf(100));
    }

    @Test
    void deleteById() {
        stubFor(WireMock.delete("/v1/api/products/101")
                .willReturn(aResponse().withStatus(204)));

        repository.deleteById(101L);

        verify(WireMock.deleteRequestedFor(urlEqualTo("/v1/api/products/101")));
    }

    private static ContentPattern<?> jsonRequest(Object object) {
        try {
            return new MatchesJsonSchemaPattern(
                    new ObjectMapper().writeValueAsString(object)
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String json(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
