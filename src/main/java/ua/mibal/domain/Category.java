package ua.mibal.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
//@Table(name = "category")
public class Category {

    //    @Id
//    @GeneratedValue
    private Long id;

    //    @Column(nullable = false, unique = true)
    private String name;
}
