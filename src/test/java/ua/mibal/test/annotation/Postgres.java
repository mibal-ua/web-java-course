package ua.mibal.test.annotation;

import org.springframework.test.context.TestPropertySource;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@TestPropertySource(properties = {
        "spring.test.database.replace=none",
        "spring.datasource.url=jdbc:tc:postgresql:15-alpine:///db",
        "spring.jpa.open-in-view=false",
        "spring.jpa.show-sql=true",
        "spring.jpa.hibernate.ddl-auto=create",
        "spring.jpa.properties.hibernate.generate_statistics=true",
})
@Target(TYPE)
@Retention(RUNTIME)
public @interface Postgres {
}
