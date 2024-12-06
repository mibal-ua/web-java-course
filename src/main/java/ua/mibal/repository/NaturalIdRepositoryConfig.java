package ua.mibal.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ua.mibal.repository.impl.NaturalIdRepositoryImpl;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Configuration
@EnableJpaRepositories(repositoryBaseClass = NaturalIdRepositoryImpl.class)
public class NaturalIdRepositoryConfig {
}
