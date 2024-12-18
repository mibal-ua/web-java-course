package ua.mibal.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {
    private static final String V1_API_ORDERS = "/api/v1/order/**";
    private static final String V1_API_ROOT = "/api/v1/**";
    private static final String API_LOGIN = "/login/**";
    private static final String API_OPENAPI = "/v3/**";

    @Bean
    @Order(1)
    public SecurityFilterChain filterChainOrdersV1(HttpSecurity http) throws Exception {
        return http
                .securityMatcher(V1_API_ORDERS)
                .cors(withDefaults())
                .csrf(CsrfConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(API_OPENAPI).permitAll()
                        .requestMatchers(V1_API_ORDERS).authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(withDefaults())
                )
                .build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain filterChainGreetingV1(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(API_LOGIN).permitAll()
                        .requestMatchers(V1_API_ROOT).authenticated()
                )
                .oauth2Login(withDefaults());
        return http.build();
    }
}
