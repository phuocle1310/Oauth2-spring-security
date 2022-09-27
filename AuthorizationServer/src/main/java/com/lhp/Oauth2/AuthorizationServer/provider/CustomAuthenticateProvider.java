// package com.lhp.Oauth2.AuthorizationServer.provider;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.authentication.AuthenticationProvider;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.AuthenticationException;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.lhp.Oauth2.AuthorizationServer.service.IUserAuthService;

// @Service
// public class CustomAuthenticateProvider implements AuthenticationProvider {
//     @Autowired
//     private IUserAuthService userService;

//     @Autowired
//     private BCryptPasswordEncoder passwordEncoder;

//     @Override
//     public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//         String username = authentication.getName();
//         String password = authentication.getCredentials().toString();
//         UserDetails user = userService.loadUserByUsername(username);
//         return checkPassword(user, password);
//     }

//     private Authentication checkPassword(UserDetails user, String rawPassword) {
//         if(passwordEncoder.matches(rawPassword, user.getPassword())) {
//             return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
//         }
//         else
//             throw new BadCredentialsException("Bad Credential");
//     }

//     @Override
//     public boolean supports(Class<?> authentication) {
//         return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//     }
// }
