package ua.mibal.repository.web.exception;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class WebServiceProductRepositoryException extends RuntimeException {

    public WebServiceProductRepositoryException(String message, HttpRequest request, ClientHttpResponse response) {
        super("Failed to execute request: " + request + " with response: " + response + " due to: " + message);
    }
}
