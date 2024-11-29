package ua.mibal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ua.mibal.test.annotation.DatabaseTest;

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
}
