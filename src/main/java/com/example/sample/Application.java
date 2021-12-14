package com.example.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.nativex.hint.TypeHint;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@TypeHint(types = {
    // remove this class and login will fail after you're redirected back to the app
    JwtDecoder.class
})
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RestController
    static class SimpleRestController {
        @GetMapping("/")
        String sayHello(@AuthenticationPrincipal OidcUser oidcUser) {
            return "Hello: " + oidcUser.getFullName();
        }
    }

}
