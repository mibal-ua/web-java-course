package ua.mibal.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.mibal.service.CategoryService;
import ua.mibal.service.exception.CategoryNotFoundException;
import ua.mibal.service.model.CategoryForm;
import ua.mibal.web.dto.CategoryDto;
import ua.mibal.web.mapper.CategoryDtoMapper;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/order/categories")
public class CategoryController {
    private final CategoryService service;
    private final CategoryDtoMapper mapper;

    @GetMapping
    public List<CategoryDto> getAll() {
        return mapper.toDto(
                service.getAll()
        );
    }

    @GetMapping("/{name}")
    public CategoryDto getOne(@PathVariable String name) {
        return mapper.toDto(
                service.getOneByName(name)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto create(@Valid @RequestBody CategoryForm category) {
        return mapper.toDto(
                service.create(category)
        );
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable String name) {
        try {
            service.deleteByName(name);
        } catch (CategoryNotFoundException e) {
        }
    }
}
