package com.jpa1prueba.mongodbmodule.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.jpa1prueba.mongodbmodule.services.ApiLogService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiLogInterceptor implements HandlerInterceptor {

    private final ApiLogService apiLogService;

    public ApiLogInterceptor(ApiLogService apiLogService) {
        this.apiLogService = apiLogService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // Registrar la llamada a la API
        apiLogService.logApiCall(request);
        return true; // Continuar con la solicitud
    }
}