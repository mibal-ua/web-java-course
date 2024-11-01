package ua.mibal.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@ConfigurationProperties(prefix = "application.rest-client")
public record RestClientProps(
        int responseTimeout
) {
}
