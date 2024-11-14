package ua.mibal.featureToggle.prop;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "feature")
public class FeatureToggleProps {
    private final Map<String, Boolean> toggles;

    public boolean isEnabled(String toggle) {
        return toggles.getOrDefault(toggle + ".enabled", false);
    }
}
