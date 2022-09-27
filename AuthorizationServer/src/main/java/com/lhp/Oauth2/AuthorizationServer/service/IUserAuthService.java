package com.lhp.Oauth2.AuthorizationServer.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.lhp.Oauth2.AuthorizationServer.pojo.User;

public interface IUserAuthService extends UserDetailsService {
    User findUserByUsername(String username);
}
