package ua.mibal.repository.impl;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import ua.mibal.repository.NaturalIdRepository;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class NaturalIdRepositoryImpl<T, ID extends Serializable, NID extends Serializable> extends SimpleJpaRepository<T, ID> implements NaturalIdRepository<T, ID, NID> {
    private final EntityManager entityManager;

    public NaturalIdRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
                                   EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<T> findByNaturalId(NID naturalId) {
        return entityManager.unwrap(Session.class)
                .bySimpleNaturalId(this.getDomainClass())
                .loadOptional(naturalId);
    }

    @Override
    public void deleteByNaturalId(NID naturalId) {
        findByNaturalId(naturalId)
                .ifPresent(this::delete);
    }

    @Override
    public boolean existsByNaturalId(NID naturalId) {
        return findByNaturalId(naturalId)
                .isPresent();
    }
}
