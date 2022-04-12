package io.javabrains.inbox.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {
    @GetMapping(value = "/user")
    public String user(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null || !StringUtils.hasText(principal.getAttribute("login"))) {
            return "not login";
        }
        return principal.getAttribute("name").toString();
    }
}
