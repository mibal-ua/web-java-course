package ua.mibal.featureToggle;

import org.springframework.stereotype.Service;
import ua.mibal.featureToggle.prop.FeatureToggleProps;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Service
public class FeatureToggleService {
    private final ConcurrentHashMap<String, Boolean> toggles;

    public FeatureToggleService(FeatureToggleProps toggleProps) {
        this.toggles = new ConcurrentHashMap<>(toggleProps.getToggles());
    }

    public boolean isEnabled(String toggle) {
        return toggles.getOrDefault(toggle + ".enabled", false);
    }

    public void enable(String feature) {
        toggles.put(feature + ".enabled", true);
    }

    public void disable(String feature) {
        toggles.put(feature + ".enabled", false);
    }
}
