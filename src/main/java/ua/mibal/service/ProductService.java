package ua.mibal.service;

import org.springframework.stereotype.Service;
import ua.mibal.domain.Product;
import ua.mibal.service.model.ProductForm;

import java.util.List;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Service
public class ProductService {
    //todo

    public List<Product> getAll() {
        return null;
    }

    public Product getOneById(Long id) {
        return null;
    }

    public Product create(ProductForm product) {
        return null;
    }

    public Product update(Long id, ProductForm product) {
        return null;
    }

    public void deleteById(Long id) {
    }
}
