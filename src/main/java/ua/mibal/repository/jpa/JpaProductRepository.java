package ua.mibal.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.mibal.domain.Product;
import ua.mibal.repository.ProductRepository;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public interface JpaProductRepository extends JpaRepository<Product, Long>, ProductRepository {
    
    boolean existsByName(String name);
}
