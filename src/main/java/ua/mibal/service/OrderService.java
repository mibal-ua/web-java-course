package ua.mibal.service;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.mibal.domain.Order;
import ua.mibal.repository.OrderRepository;
import ua.mibal.repository.ProductRepository;
import ua.mibal.repository.entity.OrderEntity;
import ua.mibal.repository.projection.ProductOrderingStatistics;
import ua.mibal.service.exception.OrderNotFoundException;
import ua.mibal.service.exception.ProductNotFoundException;
import ua.mibal.service.mapper.OrderMapper;
import ua.mibal.service.model.OrderForm;

import java.util.List;
import java.util.Optional;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository repository;
    private final ProductRepository orderRepository;
    private final OrderMapper mapper;

    @Transactional(readOnly = true)
    public List<Order> getAll() {
        return mapper.toModel(
                repository.findAll()
        );
    }

    @Transactional(readOnly = true)
    public Order getOneById(Long id) {
        return mapper.toModel(
                repository.findById(id)
                        .orElseThrow(OrderNotFoundException::new)
        );
    }

    public Order create(OrderForm order) {
        validate(order);
        return mapper.toModel(
                repository.save(mapper.toEntity(order))
        );
    }

    @Transactional
    public Order update(Long id, OrderForm form) {
        validate(form);
        Optional<OrderEntity> optionalOrder = repository.findById(id);
        if (optionalOrder.isEmpty()) {
            OrderEntity order = mapper.toEntity(id, form);
            return mapper.toModel(
                    repository.save(order)
            );
        }
        OrderEntity order = optionalOrder.get();
        mapper.update(order, form);
        repository.save(order);
        return mapper.toModel(
                order
        );
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<ProductOrderingStatistics> getProductOrderingStatistics() {
        return repository.getProductOrderingStatistics();
    }

    private void validate(OrderForm order) {
        validateQuantityAvailable(order);
    }

    private void validateQuantityAvailable(OrderForm order) {
        // TODO check available quantity of product
        validateProductExists(order.productId());
    }

    private void validateProductExists(@NotNull Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new ProductNotFoundException();
        }
    }
}
