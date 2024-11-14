package ua.mibal.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import ua.mibal.test.featureToggle.FeatureToggleExtension;
import ua.mibal.test.featureToggle.annotation.DisableFeature;
import ua.mibal.test.featureToggle.annotation.EnableFeature;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.mibal.featureToggle.ToggleableFeature.COSMO_CAT;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@ExtendWith(FeatureToggleExtension.class)
class CosmoCatControllerIntegrationTest extends IntegrationTest {
    @Autowired
    private MockMvc mvc;

    @Test
    @EnableFeature(COSMO_CAT)
    void getCosmoCats_shouldReturnIfFeatureIsEnabled() throws Exception {
        mvc.perform(get("/v1/api/cosmo-cats"))
                .andExpect(status().isOk());
    }

    @Test
    @DisableFeature(COSMO_CAT)
    void getCosmoCats_shouldThrowIfFeatureIsDisabled() throws Exception {
        mvc.perform(get("/v1/api/cosmo-cats"))
                .andExpect(status().isNotFound());
    }
}

