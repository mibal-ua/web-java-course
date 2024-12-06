package ua.mibal.service.exception;

import ua.mibal.domain.Order;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class OrderNotFoundException extends NotFoundException {

    public OrderNotFoundException() {
        super(Order.class);
    }
}
