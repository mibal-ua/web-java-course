package ua.mibal.featureToggle.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
*/
@Getter
@RequiredArgsConstructor
public enum ToggleableFeature {
    COSMO_CAT("cosmo-cat");

    private final String name;
}
