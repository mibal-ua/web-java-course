package ua.mibal.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.mibal.service.OrderService;
import ua.mibal.service.exception.OrderNotFoundException;
import ua.mibal.service.model.OrderForm;
import ua.mibal.web.dto.OrderDto;
import ua.mibal.web.mapper.OrderDtoMapper;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/orders")
public class OrderController {
    private final OrderService service;
    private final OrderDtoMapper mapper;

    @GetMapping
    public List<OrderDto> getAll() {
        return mapper.toDto(
                service.getAll()
        );
    }

    @GetMapping("/{id}")
    public OrderDto getOne(@PathVariable Long id) {
        return mapper.toDto(
                service.getOneById(id)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto create(@Valid @RequestBody OrderForm category) {
        return mapper.toDto(
                service.create(category)
        );
    }

    @PutMapping("/{id}")
    public OrderDto update(
            @PathVariable Long id,
            @Valid @RequestBody OrderForm category
    ) {
        return mapper.toDto(
                service.update(id, category)
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
        } catch (OrderNotFoundException e) {
        }
    }
}
