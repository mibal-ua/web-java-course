package ua.mibal.test.featureToggle.annotation;

import ua.mibal.featureToggle.model.ToggleableFeature;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
public @interface DisableFeature {

    ToggleableFeature value();
}
