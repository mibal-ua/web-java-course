package ua.mibal.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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
//@Table(name = "\"order\"")
public class Order {

    //    @Id
//    @GeneratedValue
    private Long id;

    //    @ManyToOne(fetch = LAZY, optional = false)
//    @JoinColumn(name = "product_id")
    private Product product;

    //    @Column(nullable = false)
    private int quantity;

    //    @Column(nullable = false)
    private Date timestamp;
}
