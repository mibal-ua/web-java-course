package ua.mibal.service.exception;

import ua.mibal.domain.Product;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class ProductNotFoundException extends NotFoundException {

    public ProductNotFoundException() {
        super(Product.class);
    }
}
