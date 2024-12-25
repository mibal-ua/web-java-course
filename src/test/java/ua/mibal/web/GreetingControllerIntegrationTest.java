package ua.mibal.web;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
class GreetingControllerIntegrationTest extends IntegrationTest {

    @Test
    void getAll() throws Exception {
        mvc.perform(get("/api/v1/greetings")
                        .with(oauth2GithubLogin("Michael Jackson")))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Michael Jackson you did good job!"));
    }
}
