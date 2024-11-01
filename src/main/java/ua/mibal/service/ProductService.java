package ua.mibal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.mibal.domain.Product;
import ua.mibal.domain.UpdateResult;
import ua.mibal.repository.ProductRepository;
import ua.mibal.service.exception.ConflictException;
import ua.mibal.service.exception.ProductNotFoundException;
import ua.mibal.service.mapper.ProductMapper;
import ua.mibal.service.model.ProductForm;

import java.util.List;
import java.util.Optional;

import static ua.mibal.domain.UpdateResult.Type.CREATED;
import static ua.mibal.domain.UpdateResult.Type.UPDATED;

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
                .orElseThrow(ProductNotFoundException::new);
    }

    public Product create(ProductForm product) {
        validateUnique(product.name());
        return repository.save(mapper.toEntity(product));
    }

    public UpdateResult<Product> update(Long id, ProductForm form) {
        validateUnique(form.name());
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isEmpty()) {
            Product product = mapper.toEntity(id, form);
            return new UpdateResult<>(
                    repository.save(product),
                    CREATED
            );
        }
        Product product = optionalProduct.get();
        mapper.update(product, form);
        repository.save(product);
        return new UpdateResult<>(
                product,
                UPDATED
        );
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private void validateUnique(String name) {
        if (repository.existsByName(name)) {
            throw new ConflictException("Product with name " + name + " already exists");
        }
    }
}
