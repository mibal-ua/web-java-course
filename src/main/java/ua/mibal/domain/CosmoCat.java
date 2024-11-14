package ua.mibal.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CosmoCat {
    private String name;
    private String color;
    private String breed;
    private String description;
}
