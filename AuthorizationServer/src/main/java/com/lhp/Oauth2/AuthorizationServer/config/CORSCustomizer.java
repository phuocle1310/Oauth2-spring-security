package com.lhp.Oauth2.AuthorizationServer.config;

import java.util.List;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Component
public class CORSCustomizer {
    public void corsCustomizer(HttpSecurity http) throws Exception {
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
    }
}
