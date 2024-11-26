package ua.mibal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import ua.mibal.web.config.MapperConfig;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@Import(MapperConfig.class)
public abstract class ControllerTest {

    @Autowired
    protected MockMvc mvc;
}
