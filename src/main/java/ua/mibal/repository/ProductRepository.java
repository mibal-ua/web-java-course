package ua.mibal.repository;

import ua.mibal.domain.Product;

import java.util.List;
import java.util.Optional;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public interface ProductRepository {

    boolean existsByName(String name);

    Optional<Product> findById(Long id);

    List<Product> findAll();

    Product save(Product entity);

    void deleteById(Long id);
}
