package net.smihi.authenticationservice.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthManager implements AuthenticationManager {
    private final AuthProvider provider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.printf("ui dkhalt l authentication Manager");
        Authentication authenticate = provider.authenticate(authentication);
        return authenticate;
    }
}
