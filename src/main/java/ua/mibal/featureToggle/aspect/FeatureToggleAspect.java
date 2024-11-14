package ua.mibal.featureToggle.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import ua.mibal.featureToggle.exception.FeatureToggleException;
import ua.mibal.featureToggle.model.FeatureToggle;
import ua.mibal.featureToggle.model.ToggleableFeature;
import ua.mibal.featureToggle.prop.FeatureToggleProps;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@RequiredArgsConstructor
@Aspect
@Component
public class FeatureToggleAspect {
    private final FeatureToggleProps props;
    
    @Before("@annotation(featureToggle)")
    public void before(FeatureToggle featureToggle) {
        checkIsEnabled(featureToggle.value());
    }

    private void checkIsEnabled(ToggleableFeature feature) {
        if (props.isEnabled(feature.getName())) {
            return;
        }
        throw new FeatureToggleException("Feature is disabled");
    }
}
