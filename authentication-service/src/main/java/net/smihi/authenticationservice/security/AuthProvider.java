package net.smihi.authenticationservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthProvider implements AuthenticationProvider {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        // Charger les détails de l'utilisateur à partir de UserDetailsService
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // Comparer le mot de passe fourni avec le mot de passe de l'utilisateur chargé
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            // Si le mot de passe est valide, retourner un objet d'authentification avec les détails de l'utilisateur
            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        } else {
            // Si le mot de passe est incorrect, lever une exception d'authentification
            throw new AuthenticationException("Mot de passe incorrect") {};
        }
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
