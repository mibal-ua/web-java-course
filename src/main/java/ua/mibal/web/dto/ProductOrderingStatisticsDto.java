package ua.mibal.web.dto;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public record ProductOrderingStatisticsDto(
        Long productId,
        String productName,
        Integer orderingCount
) {
}
