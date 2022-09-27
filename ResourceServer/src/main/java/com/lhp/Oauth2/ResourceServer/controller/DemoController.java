package com.lhp.Oauth2.ResourceServer.controller;

import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionAuthenticatedPrincipal;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class DemoController {

    @GetMapping(value="/")
    public String demo(HttpServletRequest http) {
        String tokenValue = http.getHeader("Authorization").replace("Bearer ", "").trim();
        
        return tokenValue;
        // return "Htkkko";
    }
    
    @GetMapping("/user")
    public String getUserPrincipal(Principal user) {
        return user.getName();
    }
}
