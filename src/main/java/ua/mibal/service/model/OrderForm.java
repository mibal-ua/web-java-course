package ua.mibal.service.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Getter
@Builder
public final class OrderForm {

    @Setter
    @Null
    private Long id;

    @NotNull
    private Long productId;

    @Min(1)
    @Max(100)
    private int quantity;
}
