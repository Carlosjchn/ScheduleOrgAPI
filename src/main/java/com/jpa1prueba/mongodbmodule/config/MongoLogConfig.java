package com.jpa1prueba.mongodbmodule.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jpa1prueba.mongodbmodule.interceptors.ApiLogInterceptor;

@Configuration
public class MongoLogConfig implements WebMvcConfigurer {

    private final ApiLogInterceptor apiLogInterceptor;

    public MongoLogConfig(ApiLogInterceptor apiLogInterceptor) {
        this.apiLogInterceptor = apiLogInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiLogInterceptor)
                .addPathPatterns("/api/**") // Registrar todas las llamadas a la API
                .excludePathPatterns("/api/logs/**"); // Excluir los endpoints de logs para evitar bucles infinitos
    }
}