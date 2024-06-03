package bigbowl.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfigurationSource;
import java.util.Arrays;

@Configuration
public class CorsConfig {

    private static final String[] ALLOWED_ORIGINS = new String[] {
            "*",
            "https://example.com/",
            "http://127.0.0.1:5500",
            "http://127.0.0.1:5173-5173",
            "http://127.0.0.1:5173-5174",
            "http://127.0.0.1:5173-5175"
    };
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true); // Tillad credentials

        configuration.setAllowedOriginPatterns(Arrays.asList(ALLOWED_ORIGINS));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
