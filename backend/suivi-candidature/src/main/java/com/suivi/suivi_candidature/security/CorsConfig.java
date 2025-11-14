package com.suivi.suivi_candidature.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // Autoriser ton front spécifique. NE PAS utiliser "*" si allowCredentials=true
        config.setAllowedOrigins(List.of("http://localhost:5173"));

        // Autoriser les méthodes dont le preflight a besoin
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Autoriser tous les headers (utile pendant le dev)
        config.setAllowedHeaders(List.of("*"));

        // Si tu envoies les cookies / credentials (Authorization en en-tête n'est pas cookie, mais bon)
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Appliquer à toutes les routes
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
