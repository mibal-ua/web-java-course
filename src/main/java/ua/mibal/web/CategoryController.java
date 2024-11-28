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
import ua.mibal.service.CategoryService;
import ua.mibal.service.exception.CategoryNotFoundException;
import ua.mibal.service.mapper.CategoryMapper;
import ua.mibal.service.model.CategoryForm;
import ua.mibal.web.dto.CategoryDto;

import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/categories")
public class CategoryController {
    private final CategoryService service;
    private final CategoryMapper mapper;

    @GetMapping
    public List<CategoryDto> getAll() {
        return mapper.toDto(
                service.getAll()
        );
    }

    @GetMapping("/{id}")
    public CategoryDto getOne(@PathVariable Long id) {
        return mapper.toDto(
                service.getOneById(id)
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto create(@Valid @RequestBody CategoryForm category) {
        return mapper.toDto(
                service.create(category)
        );
    }

    @PutMapping("/{id}")
    public CategoryDto update(
            @PathVariable Long id,
            @Valid @RequestBody CategoryForm category
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
        } catch (CategoryNotFoundException e) {
        }
    }
}
