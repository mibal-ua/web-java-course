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
                p.id AS productId,
                p.name AS productName,
                COALESCE(SUM(o.quantity), 0) AS orderingCount
            FROM ProductEntity p
                LEFT JOIN p.orders o
            GROUP BY p.id, p.name
            ORDER BY p.name
            """)
    List<ProductOrderingStatistics> getProductOrderingStatistics();
}
