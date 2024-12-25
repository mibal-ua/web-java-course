package ua.mibal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import ua.mibal.test.annotation.DatabaseTest;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@DatabaseTest
@AutoConfigureMockMvc
@SpringBootTest
public abstract class IntegrationTest {

    @Autowired
    protected MockMvc mvc;

    protected RequestPostProcessor oauth2GithubLogin(String name) {
        return oauth2Login()
                .attributes((attrs) -> attrs.put(
                        "name", name
                ))
                .authorities(() -> "SCOPE_user:email");
    }
}
