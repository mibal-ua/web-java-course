package ua.mibal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@NoRepositoryBean
public interface NaturalIdRepository<T, ID> extends JpaRepository<T, ID> {

    Optional<T> findByNaturalId(ID naturalId);

    void deleteByNaturalId(ID naturalId);
}
