package ua.mibal.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Getter
@Setter
@Entity
@Table(name = "categories")
public class CategoryEntity {
    
    @Id @GeneratedValue
    private Long id;
    
    @NaturalId
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
