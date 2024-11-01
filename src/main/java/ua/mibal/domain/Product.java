package ua.mibal.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

//@Entity
//@Table(name = "product")
public class Product {

    //    @Id
//    @GeneratedValue
    private Long id;

    //    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    //    @Column(nullable = false)
    private BigDecimal price;

    //    @ManyToOne(optional = false, fetch = LAZY)
//    @JoinColumn(name = "category_id")
    private Category category;
}
