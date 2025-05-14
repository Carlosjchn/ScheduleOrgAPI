package com.jpa1prueba.mongodbmodule.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jpa1prueba.mongodbmodule.entities.ApiLog;
import com.jpa1prueba.mongodbmodule.repositories.ApiLogRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ApiLogService {

    private final ApiLogRepository apiLogRepository;

    public ApiLogService(ApiLogRepository apiLogRepository) {
        this.apiLogRepository = apiLogRepository;
    }

    public void logApiCall(HttpServletRequest request) {
        ApiLog log = ApiLog.builder()
                .endpoint(request.getRequestURI())
                .method(request.getMethod())
                .requestIp(request.getRemoteAddr())
                .timestamp(LocalDateTime.now())
                .userAgent(request.getHeader("User-Agent"))
                .build();
        
        apiLogRepository.save(log);
    }

    public List<ApiLog> getAllLogs() {
        return apiLogRepository.findAll();
    }

    public void clearAllLogs() {
        apiLogRepository.deleteAll();
    }
}