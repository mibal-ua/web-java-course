package ua.mibal.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.mibal.service.exception.EntityNotFoundException;

import static java.net.URI.create;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Slf4j
@RestControllerAdvice
public class ExceptionInterceptor {

    @ExceptionHandler(EntityNotFoundException.class)
    ProblemDetail handle(EntityNotFoundException e) {
        log.info("Entity Not Found exception caught: " + e.getMessage());
        ProblemDetail problemDetail = forStatusAndDetail(NOT_FOUND, e.getMessage());
        problemDetail.setType(create(e.getType()));
        problemDetail.setTitle(e.getTitle());
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    ProblemDetail handle(Exception e) {
        log.warn("Unknown exception caught: " + e.getMessage());
        ProblemDetail problemDetail = forStatusAndDetail(INTERNAL_SERVER_ERROR, e.getMessage());
        problemDetail.setTitle("Internal Server Error. Contact support");
        return problemDetail;
    }
}
