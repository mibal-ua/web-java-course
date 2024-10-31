package ua.mibal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.mibal.domain.Product;
import ua.mibal.repository.ProductRepository;
import ua.mibal.service.exception.EntityNotFoundException;
import ua.mibal.service.mapper.ProductMapper;
import ua.mibal.service.model.ProductForm;

import java.util.List;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product getOneById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    public Product create(ProductForm product) {
        return repository.save(mapper.toEntity(product));
    }

    public Product update(Long id, ProductForm product) {
        //todo
        return null;
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
