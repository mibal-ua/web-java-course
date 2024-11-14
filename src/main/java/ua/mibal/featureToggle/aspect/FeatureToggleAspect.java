package ua.mibal.featureToggle.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ua.mibal.featureToggle.FeatureToggleService;
import ua.mibal.featureToggle.exception.FeatureToggleException;
import ua.mibal.featureToggle.model.FeatureToggle;
import ua.mibal.featureToggle.model.ToggleableFeature;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@RequiredArgsConstructor
@Aspect
@Component
public class FeatureToggleAspect {
    private final FeatureToggleService service;
    
    @Before("@annotation(featureToggle)")
    public void before(FeatureToggle featureToggle) {
        checkIsEnabled(featureToggle.value());
    }

    private void checkIsEnabled(ToggleableFeature feature) {
        if (service.isEnabled(feature.getName())) {
            return;
        }
        throw new FeatureToggleException("Feature is disabled");
    }
}
