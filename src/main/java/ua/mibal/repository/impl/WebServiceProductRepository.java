package ua.mibal.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ua.mibal.domain.Product;
import ua.mibal.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@RequiredArgsConstructor
@Primary
@Service
public class WebServiceProductRepository implements ProductRepository {
    private final RestClient restClient;

    @Override
    public boolean existsByName(String name) {
        return false;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public Product save(Product entity) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
