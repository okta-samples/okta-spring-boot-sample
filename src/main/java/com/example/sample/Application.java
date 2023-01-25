package com.example.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.security.Principal;
import java.util.Collections;

@SpringBootApplication
@EnableMethodSecurity(securedEnabled = true)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * This example controller has endpoints for displaying the user profile info on {code}/{code} and "you have been
     * logged out page" on {code}/post-logout{code}.
     */
    @Controller
    static class ExampleController {

        @GetMapping("/")
        String home() {
            return "home";
        }

        @GetMapping("/profile")
        @PreAuthorize("hasAuthority('SCOPE_profile')")
        ModelAndView userDetails(OAuth2AuthenticationToken authentication) {
            return new ModelAndView("userProfile" , Collections.singletonMap("details", authentication.getPrincipal().getAttributes()));
        }
    }

    @RestController
    static class ExampleRestController {

        @GetMapping("/hello")
        String sayHello(@AuthenticationPrincipal OidcUser oidcUser) {
            return "Hello: " + oidcUser.getFullName();
        }
    }
}
