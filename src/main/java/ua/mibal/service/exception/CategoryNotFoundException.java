package ua.mibal.service.exception;

import ua.mibal.domain.Category;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class CategoryNotFoundException extends NotFoundException {

    public CategoryNotFoundException() {
        super(Category.class);
    }
}
