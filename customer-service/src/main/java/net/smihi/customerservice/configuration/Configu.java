package net.smihi.customerservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
public class Configu {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // Autorise Angular à accéder
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // Méthodes HTTP autorisées
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type")); // En-têtes autorisées
        configuration.setAllowCredentials(true); // Si vous utilisez des cookies ou des sessions
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Appliquer CORS à tous les endpoints
        return new CorsFilter(source);
    }
}
