package ua.mibal.repository.web.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ua.mibal.repository.web.config.props.WebServiceProductRepositoryProps;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@EnableConfigurationProperties(WebServiceProductRepositoryProps.class)
@Configuration
public class WebServiceProductRepositoryConfiguration {
}
