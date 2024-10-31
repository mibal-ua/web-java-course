package ua.mibal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@ComponentScan("ua.mibal.web.mapper")
public abstract class ControllerTest {

    @Autowired
    protected MockMvc mvc;
}
