package com.jpa1prueba.scheduleorganizermodule.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jpa1prueba.scheduleorganizermodule.dtos.OrganizerRequestDTO;
import com.jpa1prueba.scheduleorganizermodule.dtos.ScheduleResponseDTO;
import com.jpa1prueba.scheduleorganizermodule.dtos.SaveScheduleRequestDTO;
import com.jpa1prueba.jpademo.entities.HorariosComun;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrganizerServiceImpl implements OrganizerService {
    
    private static final Logger logger = LoggerFactory.getLogger(OrganizerServiceImpl.class);
    private final RestTemplate restTemplate;
    private final HorarioConversionService horarioConversionService;
    private final ObjectMapper objectMapper;
    
    @Value("${schedule.service.base-url:http://localhost:8000}")
    private String baseUrl;

    @Override
    public ScheduleResponseDTO generateScheduleWithAI(OrganizerRequestDTO request) {
        return callScheduleService("/schedule/generate", request);
    }

    @Override
    public ScheduleResponseDTO generateScheduleWithORTools(OrganizerRequestDTO request) {
        return callScheduleService("/solver/optimize", request);
    }

    public HorariosComun saveSchedule(SaveScheduleRequestDTO saveRequest, OrganizerRequestDTO originalRequest) {
        ScheduleResponseDTO scheduleResponse;
        try {
            scheduleResponse = objectMapper.convertValue(saveRequest.getScheduleData(), ScheduleResponseDTO.class);
            return horarioConversionService.convertAndSave(scheduleResponse, originalRequest);
        } catch (Exception e) {
            logger.error("Error converting or saving schedule data", e);
            throw new RuntimeException("Error saving schedule data", e);
        }
    }

    private ScheduleResponseDTO callScheduleService(String endpoint, OrganizerRequestDTO request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(java.util.Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<OrganizerRequestDTO> entity = new HttpEntity<>(request, headers);
        
        try {
            logger.info("Calling microservice endpoint: {}", baseUrl + endpoint);
            logger.debug("Request payload: {}", objectMapper.writeValueAsString(request));
            
            ScheduleResponseDTO response = restTemplate.postForObject(
                baseUrl + endpoint,
                entity,
                ScheduleResponseDTO.class
            );
            
            logger.debug("Microservice response: {}", objectMapper.writeValueAsString(response));
            return response;
            
        } catch (Exception e) {
            logger.error("Error calling schedule service", e);
            throw new RuntimeException("Error generating schedule", e);
        }
    }
} 