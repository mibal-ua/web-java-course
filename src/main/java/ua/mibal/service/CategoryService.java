package ua.mibal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.mibal.domain.Category;
import ua.mibal.repository.CategoryRepository;
import ua.mibal.repository.entity.CategoryEntity;
import ua.mibal.service.exception.CategoryNotFoundException;
import ua.mibal.service.exception.ConflictException;
import ua.mibal.service.mapper.CategoryMapper;
import ua.mibal.service.model.CategoryForm;

import java.util.List;
import java.util.Optional;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Transactional(readOnly = true)
    public List<Category> getAll() {
        return mapper.toModel(
                repository.findAll()
        );
    }

    @Transactional(readOnly = true)
    public Category getOneByName(String name) {
        return mapper.toModel(
                repository.findById(name)
                        .orElseThrow(CategoryNotFoundException::new)
        );
    }

    public Category create(CategoryForm product) {
        validateUnique(product.name());
        return mapper.toModel(
                repository.save(mapper.toEntity(product))
        );
    }

    @Transactional
    public Category update(String name, CategoryForm form) {
        validateUnique(form.name());
        Optional<CategoryEntity> optionalCategory = repository.findById(name);
        if (optionalCategory.isEmpty()) {
            CategoryEntity product = mapper.toEntity(name, form);
            return mapper.toModel(
                    repository.save(product)
            );
        }
        CategoryEntity product = optionalCategory.get();
        mapper.update(product, form);
        repository.save(product);
        return mapper.toModel(
                product
        );
    }

    public void deleteByName(String name) {
        repository.deleteById(name);
    }

    private void validateUnique(String name) {
        if (repository.existsByName(name)) {
            throw new ConflictException("Category with name " + name + " already exists");
        }
    }
}
