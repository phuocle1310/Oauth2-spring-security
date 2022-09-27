package com.lhp.Oauth2.ResourceServer.config;

import java.util.List;

import javax.servlet.http.Cookie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(c -> {
            CorsConfigurationSource source = s -> {
                CorsConfiguration corsConfig = new CorsConfiguration();
                corsConfig.setAllowCredentials(true);
                corsConfig.setAllowedOrigins(List.of("http://127.0.0.1:3000"));
                corsConfig.setAllowedHeaders(List.of("*"));
                corsConfig.setAllowedMethods(List.of("*"));

                return corsConfig;
            };

            c.configurationSource(source);
        });

        http.logout()
        .clearAuthentication(true)
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID");
        return http.oauth2ResourceServer(
            j -> j.jwt().jwkSetUri("http://localhost:8080/oauth2/jwks")
        ).authorizeRequests()
            .anyRequest().authenticated()
            .and().build();
    }
}
