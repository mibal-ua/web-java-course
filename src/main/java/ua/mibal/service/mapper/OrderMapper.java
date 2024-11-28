package ua.mibal.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import ua.mibal.domain.Order;
import ua.mibal.repository.entity.OrderEntity;
import ua.mibal.service.model.OrderForm;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Mapper(componentModel = SPRING)
public interface OrderMapper {
    List<Order> toModel(List<OrderEntity> list);

    Order toModel(OrderEntity orderEntity);

    OrderEntity toEntity(OrderForm product);

    OrderEntity toEntity(Long id, OrderForm product);

    void update(@MappingTarget OrderEntity order, OrderForm form);
}
