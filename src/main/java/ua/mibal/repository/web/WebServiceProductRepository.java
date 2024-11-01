package ua.mibal.repository.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClient;
import ua.mibal.domain.Product;
import ua.mibal.repository.ProductRepository;
import ua.mibal.repository.web.config.props.WebServiceProductRepositoryProps;
import ua.mibal.repository.web.exception.WebServiceProductRepositoryException;
import ua.mibal.service.mapper.ProductMapper;
import ua.mibal.web.dto.ProductDto;
import ua.mibal.web.mapper.ProductDtoMapper;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Slf4j
@RequiredArgsConstructor
@Primary
@Service
public class WebServiceProductRepository implements ProductRepository {
    private final RestClient restClient;
    private final WebServiceProductRepositoryProps props;
    private final ProductDtoMapper productDtoMapper;
    private final ProductMapper productMapper;

    public static ResponseErrorHandler ignore404() {
        return new ResponseErrorHandler() {

            @Override
            public boolean hasError(final ClientHttpResponse response) throws IOException {
                return response.getStatusCode() == HttpStatus.NOT_FOUND;
            }

            @Override
            public void handleError(final ClientHttpResponse response) {
                //Do nothing
            }
        };
    }

    @Override
    public boolean existsByName(String name) {
        HttpStatusCode responseCode = restClient.get()
                .uri(props.url() + "/exists?name={name}", name)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    log.error("Server response failed to check Product by name exists {}", response.getStatusCode());
                    throw new WebServiceProductRepositoryException("Failed to check Product by name exists", request, response);
                })
                .onStatus(ignore404())
                .toBodilessEntity()
                .getStatusCode();
        return responseCode.is2xxSuccessful();
    }

    @Override
    public Optional<Product> findById(Long id) {
        ResponseEntity<ProductDto> product = restClient.get()
                .uri(props.url() + "/{id}", id)
                .retrieve()
                .onStatus(ignore404())
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    log.error("Server response failed to find Product by id {}", response.getStatusCode());
                    throw new WebServiceProductRepositoryException("Failed to find Product by id", request, response);
                })
                .toEntity(ProductDto.class);
        return Optional.ofNullable(
                productDtoMapper.toEntity(product.getBody())
        );
    }

    @Override
    public List<Product> findAll() {
        List<ProductDto> products = restClient.get()
                .uri(props.url())
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    log.error("Server response failed to get all Products {}", response.getStatusCode());
                    throw new WebServiceProductRepositoryException("Failed to get all Products", request, response);
                })
                .body(new ParameterizedTypeReference<>() {
                });
        return productDtoMapper.toEntity(products);
    }

    @Override
    public Product save(Product entity) {
        if (entity.getId() == null) {
            return simpleSave(entity);
        }
        return put(entity);
    }

    @Override
    public void deleteById(Long id) {
        restClient.delete()
                .uri(props.url() + "/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    log.error("Server response failed to delete one Product by id {}", response.getStatusCode());
                    throw new WebServiceProductRepositoryException("Failed to delete one Product by id", request, response);
                });
    }

    private Product simpleSave(Product entity) {
        ProductDto product = restClient.post()
                .uri(props.url())
                .body(productMapper.toForm(entity))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    log.error("Server response failed to find Product by id {}", response.getStatusCode());
                    throw new WebServiceProductRepositoryException("Failed to find Product by id", request, response);
                })
                .body(ProductDto.class);
        return productDtoMapper.toEntity(product);
    }

    private Product put(Product entity) {
        ProductDto product = restClient.put()
                .uri(props.url() + "/{id}", entity.getId())
                .body(productMapper.toForm(entity))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, (request, response) -> {
                    log.error("Server response failed to find Product by id {}", response.getStatusCode());
                    throw new WebServiceProductRepositoryException("Failed to find Product by id", request, response);
                })
                .body(ProductDto.class);
        return productDtoMapper.toEntity(product);
    }
}
