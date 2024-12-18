package ua.mibal.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mykhailo Balakhon
 * @link <a href="mailto:mykhailo.balakhon@communify.us">mykhailo.balakhon@communify.us</a>
 */
@RestController
@RequestMapping("/api/v1/greetings")
public class GreetingController {
    
    @GetMapping
    public String greet(@AuthenticationPrincipal OAuth2User user) {
        return "Hello " + user.getAttribute("name") + " you did good job!";
    }
}
