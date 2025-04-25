package com.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfiguration {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        final List<String> allowedOrigins = List.of(
                "http://localhost:5173",
                "http://192.168.0.13:5173",
                "https://messenger.gladyshdd.ru"
        );
        final List<String> allowedMethods = List.of("HEAD", "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS");
        final List<String> allowedHeaders = List.of("*");
        final boolean allowCredentials = true;


        org.springframework.web.cors.CorsConfiguration corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
        corsConfiguration.setAllowedOrigins(
                allowedOrigins
        );
        corsConfiguration.setAllowedMethods(
                allowedMethods
        );
        corsConfiguration.setAllowedHeaders(
                allowedHeaders
        );
        corsConfiguration.setAllowCredentials(
                allowCredentials
        );
        org.springframework.web.cors.UrlBasedCorsConfigurationSource source = new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
