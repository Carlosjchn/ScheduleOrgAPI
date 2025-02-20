package com.jpa1prueba.jpademo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                    "http://localhost:8081",
                    "http://10.0.2.2:8081",
                    "http://192.168.1.*", 
                    "http://192.168.41.*",     // Your Ethernet network
                    "http://192.168.186.*",    // VMware network
                    "http://192.168.2.*",      // VMware network
                    "http://172.28.128.*",     // WSL network
                    "exp://*",                 // For Expo development
                    "http://10.0.2.2:*"        // Android emulator
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}