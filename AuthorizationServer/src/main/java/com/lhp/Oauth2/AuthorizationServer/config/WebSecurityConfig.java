package com.lhp.Oauth2.AuthorizationServer.config;

import javax.servlet.http.Cookie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

// import com.lhp.Oauth2.AuthorizationServer.provider.CustomAuthenticateProvider;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class WebSecurityConfig {
    // private final CustomAuthenticateProvider customAuthenticationProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final CORSCustomizer corsCustomizer;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        corsCustomizer.corsCustomizer(http);
        http.logout()
        .clearAuthentication(true).deleteCookies("JSESSIONID").invalidateHttpSession(true);
        return http.formLogin().and().authorizeRequests().anyRequest().authenticated()
                .and().build();
    }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     var u1 =  User.withUsername("admin").password("123").authorities("read").build();
    //     var uds = new InMemoryUserDetailsManager();
    //     uds.createUser(u1);
    //     return uds;
    // }

    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
    
    // public final void bindAuthenticationProvider(AuthenticationManagerBuilder auth) {
    //     auth.authenticationProvider(customAuthenticationProvider);
    // }
}
