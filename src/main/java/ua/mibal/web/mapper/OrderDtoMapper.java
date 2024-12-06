package ua.mibal.web.mapper;

import org.mapstruct.Mapper;
import ua.mibal.domain.Order;
import ua.mibal.repository.projection.ProductOrderingStatistics;
import ua.mibal.web.dto.OrderDto;
import ua.mibal.web.dto.ProductOrderingStatisticsDto;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Mapper(componentModel = SPRING)
public interface OrderDtoMapper {

    List<OrderDto> toDto(List<Order> orders);

    OrderDto toDto(Order order);

    List<ProductOrderingStatisticsDto> toStatisticsDto(List<ProductOrderingStatistics> productOrderingStatistics);
}
