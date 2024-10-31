package ua.mibal.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.mibal.service.ProductService;
import ua.mibal.service.model.ProductForm;
import ua.mibal.web.dto.ProductDto;
import ua.mibal.web.mapper.ProductDtoMapper;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/products")
public class ProductController {
    private final ProductService service;
    private final ProductDtoMapper mapper;

    @GetMapping
    public List<ProductDto> getAll() {
        return mapper.toDto(
                service.getAll()
        );
    }

    @GetMapping("/{id}")
    public ProductDto getOne(@PathVariable Long id) {
        return mapper.toDto(
                service.getOneById(id)
        );
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public ProductDto create(@Valid @RequestBody ProductForm product) {
        return mapper.toDto(
                service.create(product)
        );
    }

    @PutMapping("/{id}")
    public ProductDto update(
            @PathVariable Long id,
            @Valid @RequestBody ProductForm product
    ) {
        return mapper.toDto(
                service.update(id, product)
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
