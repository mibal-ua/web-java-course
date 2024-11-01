package ua.mibal.repository.web.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@ConfigurationProperties(prefix = "application.web-service-product-repository")
public record WebServiceProductRepositoryProps(
        String url
) {
}
