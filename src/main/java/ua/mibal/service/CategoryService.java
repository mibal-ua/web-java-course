package ua.mibal.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import ua.mibal.domain.Category;
import ua.mibal.service.model.CategoryForm;

import java.util.List;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Service
public class CategoryService {
 
    public List<Category> getAll() {
        return null;
    }

 public Category getOneById(Long id) {
  return null;
 }

 public Category create(CategoryForm form) {
  return null;
 }

 public Category update(Long id, @Valid CategoryForm form) {
  return null;
 }

 public void deleteById(Long id) {
  
 }
}
