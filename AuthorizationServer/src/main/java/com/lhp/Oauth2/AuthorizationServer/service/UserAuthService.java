package com.lhp.Oauth2.AuthorizationServer.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lhp.Oauth2.AuthorizationServer.pojo.User;
import com.lhp.Oauth2.AuthorizationServer.repository.IUserAuthRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
public class UserAuthService implements IUserAuthService {

    @Autowired
    private IUserAuthRepository userAuthRepository;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return NoOpPasswordEncoder.getInstance();
    // }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userAuthRepository.findByUsernameLike(username).get();
        if(user == null) {
            throw new UsernameNotFoundException("No User found");
        }

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            getAuthorities(user.getRole())
            );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

    @Override
    public User findUserByUsername(String username) {
        User user = this.userAuthRepository.findByUsernameLike(username).get();
        return this.userAuthRepository.save(user);
    }
    
}
