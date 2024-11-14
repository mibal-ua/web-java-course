package ua.mibal.featureToggle.prop;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Getter
@ConfigurationProperties("feature")
public class FeatureToggleProps {
    private Map<String, Boolean> toggles = new HashMap<>();
}
