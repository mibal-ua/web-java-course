package ua.mibal.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.OK;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public record UpdateResult<T>(
        T entity,
        Type type
) {
    @Getter
    @RequiredArgsConstructor
    public enum Type {
        CREATED(HttpStatus.CREATED),
        UPDATED(OK);
        
        private final HttpStatus httpCode;
    }
}
