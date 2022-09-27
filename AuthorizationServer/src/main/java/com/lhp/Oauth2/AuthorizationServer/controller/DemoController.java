package com.lhp.Oauth2.AuthorizationServer.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.authorization.config.ConfigurationSettingNames.Token;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lhp.Oauth2.AuthorizationServer.pojo.User;

@RestController
public class DemoController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping(value="/")
    public String demo() {
        return "<a href=\"/logout\">Logout</a>";
    }
}
