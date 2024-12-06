package ua.mibal.test.featureToggle;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ua.mibal.featureToggle.FeatureToggleService;
import ua.mibal.test.featureToggle.annotation.DisableFeature;
import ua.mibal.test.featureToggle.annotation.EnableFeature;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public class FeatureToggleExtension implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        context.getTestMethod().ifPresent(method -> {
            FeatureToggleService service = getFeatureToggleService(context);
            if (method.isAnnotationPresent(EnableFeature.class)) {
                EnableFeature feature = method.getAnnotation(EnableFeature.class);
                service.enable(feature.value().getName());
            } else if (method.isAnnotationPresent(DisableFeature.class)) {
                DisableFeature feature = method.getAnnotation(DisableFeature.class);
                service.disable(feature.value().getName());
            }
        });
    }

    @Override
    public void afterEach(ExtensionContext context) {
        context.getTestMethod().ifPresent(method -> {
            FeatureToggleService service = getFeatureToggleService(context);
            if (method.isAnnotationPresent(EnableFeature.class)) {
                EnableFeature feature = method.getAnnotation(EnableFeature.class);
                service.disable(feature.value().getName());
            } else if (method.isAnnotationPresent(DisableFeature.class)) {
                DisableFeature feature = method.getAnnotation(DisableFeature.class);
                service.enable(feature.value().getName());
            }
        });
    }

    private FeatureToggleService getFeatureToggleService(ExtensionContext context) {
        return SpringExtension.getApplicationContext(context)
                .getBean(FeatureToggleService.class);
    }
}
