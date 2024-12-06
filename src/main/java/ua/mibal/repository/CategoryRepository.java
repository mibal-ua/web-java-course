package ua.mibal.repository;

import ua.mibal.repository.entity.CategoryEntity;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public interface CategoryRepository extends NaturalIdRepository<CategoryEntity, Long, String> {
}
