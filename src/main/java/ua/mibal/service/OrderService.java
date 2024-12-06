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

import java.util.Date;
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
    private final ProductRepository productRepository;

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

    @Transactional
    public Order create(OrderForm form) {
        validate(form);
        OrderEntity order = mapper.toEntity(form);
        order.setProduct(productRepository.findById(form.getProductId()).get());
        order.setTimestamp(new Date());
        return mapper.toModel(
                repository.save(order)
        );
    }

    @Transactional
    public Order update(OrderForm form) {
        validate(form);
        Optional<OrderEntity> optionalOrder = repository.findById(form.getId());
        if (optionalOrder.isEmpty()) {
            return create(form);
        }
        OrderEntity order = optionalOrder.get();
        mapper.update(order, form);
        order.setProduct(productRepository.findById(form.getProductId()).get());
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
        validateProductExists(order.getProductId());
    }

    private void validateProductExists(@NotNull Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new ProductNotFoundException();
        }
    }
}
