package ua.mibal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.mibal.domain.Product;
import ua.mibal.repository.ProductRepository;
import ua.mibal.repository.entity.ProductEntity;
import ua.mibal.service.exception.ConflictException;
import ua.mibal.service.exception.ProductNotFoundException;
import ua.mibal.service.mapper.ProductMapper;
import ua.mibal.service.model.ProductForm;

import java.util.List;
import java.util.Optional;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Transactional(readOnly = true)
    public List<Product> getAll() {
        return mapper.toModel(
                repository.findAll()
        );
    }

    @Transactional(readOnly = true)
    public Product getOneById(Long id) {
        return mapper.toModel(
                repository.findById(id)
                .orElseThrow(ProductNotFoundException::new)
        );
    }

    public Product create(ProductForm product) {
        validateUnique(product.name());
        return mapper.toModel(
                repository.save(mapper.toEntity(product))        
        );
    }

    @Transactional
    public Product update(Long id, ProductForm form) {
        validateUnique(form.name());
        Optional<ProductEntity> optionalProduct = repository.findById(id);
        if (optionalProduct.isEmpty()) {
            ProductEntity product = mapper.toEntity(id, form);
            return mapper.toModel(
                    repository.save(product)
            );
        }
        ProductEntity product = optionalProduct.get();
        mapper.update(product, form);
        repository.save(product);
        return mapper.toModel(
                product
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
