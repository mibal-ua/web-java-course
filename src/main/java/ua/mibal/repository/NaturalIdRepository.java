package ua.mibal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@NoRepositoryBean
public interface NaturalIdRepository<T, ID, NID> extends JpaRepository<T, ID> {

    Optional<T> findByNaturalId(NID naturalId);

    void deleteByNaturalId(NID naturalId);

    boolean existsByNaturalId(NID naturalId);
}
