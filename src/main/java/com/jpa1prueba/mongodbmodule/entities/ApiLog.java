package com.jpa1prueba.mongodbmodule.entities;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "api_logs")
public class ApiLog {
    
    @Id
    private String id;
    private String endpoint;
    private String method;
    private String requestIp;
    private LocalDateTime timestamp;
    private String userAgent;
}