package ua.mibal.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.test.context.support.WithMockUser;
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
@WithMockUser
@ExtendWith(FeatureToggleExtension.class)
class CosmoCatControllerIntegrationTest extends IntegrationTest {

    @Test
    @EnableFeature(COSMO_CAT)
    void getCosmoCats_shouldReturnIfFeatureIsEnabled() throws Exception {
        mvc.perform(get("/api/v1/order/cosmo-cats"))
                .andExpect(status().isOk());
    }

    @Test
    @DisableFeature(COSMO_CAT)
    void getCosmoCats_shouldThrowIfFeatureIsDisabled() throws Exception {
        mvc.perform(get("/api/v1/order/cosmo-cats"))
                .andExpect(status().isNotFound());
    }
}

