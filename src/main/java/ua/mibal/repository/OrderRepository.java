package ua.mibal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.mibal.repository.entity.OrderEntity;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
