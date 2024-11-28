package ua.mibal.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.mibal.domain.Order;
import ua.mibal.service.model.OrderForm;

import java.util.List;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@RequiredArgsConstructor
@Service
public class OrderService {
    public List<Order> getAll() {
        return null;
    }

    public Order getOneById(Long id) {
        return null;
    }

    public Order create(@Valid OrderForm form) {
        return null;
    }

    public Order update(Long id, @Valid OrderForm form) {
        return null;
    }

    public void deleteById(Long id) {
        
    }
}
