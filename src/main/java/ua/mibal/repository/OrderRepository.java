package ua.mibal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.mibal.repository.entity.OrderEntity;
import ua.mibal.repository.projection.ProductOrderingStatistics;

import java.util.List;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query("""
            SELECT
                p.id as productId,
                p.name as productName,
                COUNT(o) as orderingCount
            FROM ProductEntity p
            LEFT JOIN p.orders o
            ORDER BY p.name
            """)
    List<ProductOrderingStatistics> getProductOrderingStatistics();
}
