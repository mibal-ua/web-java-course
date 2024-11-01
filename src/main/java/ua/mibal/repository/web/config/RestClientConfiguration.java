package ua.mibal.repository.web.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestClient;
import ua.mibal.repository.web.config.props.RestClientProps;

import java.time.Duration;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@RequiredArgsConstructor
@EnableConfigurationProperties(RestClientProps.class)
@Configuration
public class RestClientConfiguration {
    private final RestClientProps restClientProps;

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .requestFactory(getClientHttpRequestFactory(restClientProps.responseTimeout()))
                .build();
    }

    private static ClientHttpRequestFactory getClientHttpRequestFactory(int responseTimeout) {
        ClientHttpRequestFactorySettings settings = ClientHttpRequestFactorySettings.DEFAULTS
                .withReadTimeout(Duration.ofMillis(responseTimeout));
        return ClientHttpRequestFactories.get(settings);
    }
}
