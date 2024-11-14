package ua.mibal.featureToggle;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ua.mibal.featureToggle.prop.FeatureToggleProps;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@EnableConfigurationProperties({
        FeatureToggleProps.class
})
@EnableAspectJAutoProxy
@Configuration
public class AopConfig {
}
