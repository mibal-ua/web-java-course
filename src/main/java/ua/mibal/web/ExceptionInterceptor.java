package ua.mibal.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.mibal.service.exception.ConflictException;
import ua.mibal.service.exception.EntityNotFoundException;
import ua.mibal.web.dto.ConstraintViolationProblemDetails;

import static java.net.URI.create;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ProblemDetail.forStatusAndDetail;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Slf4j
@RestControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    ProblemDetail handle(EntityNotFoundException e) {
        log.info("Entity Not Found exception caught: " + e.getMessage());
        ProblemDetail problemDetail = forStatusAndDetail(NOT_FOUND, e.getMessage());
        problemDetail.setType(create(e.getType()));
        problemDetail.setTitle(e.getTitle());
        return problemDetail;
    }

    @ExceptionHandler(ConflictException.class)
    ProblemDetail handle(ConflictException e) {
        log.info("Conflict exception caught: " + e.getMessage());
        ProblemDetail problemDetail = forStatusAndDetail(CONFLICT, e.getMessage());
        problemDetail.setType(create("conflict"));
        problemDetail.setTitle(e.getMessage());
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    ProblemDetail handle(Exception e) {
        log.warn("Unknown exception caught: " + e.getMessage());
        ProblemDetail problemDetail = forStatusAndDetail(INTERNAL_SERVER_ERROR, e.getMessage());
        problemDetail.setTitle("Internal Server Error. Contact support");
        return problemDetail;
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        log.info("Input params validation failed");
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(ConstraintViolationProblemDetails.of(e));
    }
}
