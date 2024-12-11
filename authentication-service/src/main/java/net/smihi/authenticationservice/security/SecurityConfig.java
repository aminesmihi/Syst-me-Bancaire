package net.smihi.authenticationservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final AuthManager authManager;
    private final LogHandler logHandler;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, @Lazy AuthManager authManager, LogHandler logHandler) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authManager = authManager;
        this.logHandler = logHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
        security.csrf(csrf->csrf.disable());
        security.authenticationManager(authManager);
        security.sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        security.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        security.authorizeHttpRequests(req->req.requestMatchers("/auth","/refreshtoken").permitAll().anyRequest().authenticated());
        security.logout(log->log.logoutUrl("/api/v1/logout")
                .addLogoutHandler(logHandler)
                .logoutSuccessHandler((request, response, authentication) -> {
                    SecurityContextHolder.clearContext();
                })
        );
        return security.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration=new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;
    }

}
