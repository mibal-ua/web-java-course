package ua.mibal.service;

import org.springframework.stereotype.Service;
import ua.mibal.domain.CosmoCat;
import ua.mibal.featureToggle.model.FeatureToggle;

import java.util.List;

import static ua.mibal.featureToggle.model.ToggleableFeature.COSMO_CAT;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Service
public class CosmoCatService {

    @FeatureToggle(COSMO_CAT)
    public List<CosmoCat> getCosmoCats() {
        return List.of(
                new CosmoCat("CosmoCat 1", "blue", "soft", "CosmoCat 1 description"),
                new CosmoCat("CosmoCat 2", "red", "hard", "CosmoCat 2 description"),
                new CosmoCat("CosmoCat 3", "green", "soft", "CosmoCat 3 description")
        );
    }
}
