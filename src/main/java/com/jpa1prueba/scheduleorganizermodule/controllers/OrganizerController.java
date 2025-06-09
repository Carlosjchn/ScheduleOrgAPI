package com.jpa1prueba.scheduleorganizermodule.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jpa1prueba.scheduleorganizermodule.dtos.OrganizerRequestDTO;
import com.jpa1prueba.scheduleorganizermodule.dtos.ScheduleResponseDTO;
import com.jpa1prueba.scheduleorganizermodule.dtos.SaveScheduleRequestDTO;
import com.jpa1prueba.scheduleorganizermodule.services.OrganizerService;
import com.jpa1prueba.jpademo.entities.HorariosComun;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/organizer")
@RequiredArgsConstructor
@Tag(name = "Schedule Organizer", description = "Endpoints for schedule generation and optimization")
public class OrganizerController {
    
    private static final Logger logger = LoggerFactory.getLogger(OrganizerController.class);
    private final OrganizerService organizerService;

    @PostMapping("/ai/generar")
    @Operation(
        summary = "Generate schedule using AI",
        description = "Generates an optimized schedule for workers using AI algorithms"
    )
    public ScheduleResponseDTO generateScheduleWithAI(@Valid @RequestBody OrganizerRequestDTO request) {
        logger.info("Received AI schedule generation request");
        return organizerService.generateScheduleWithAI(request);
    }

    @PostMapping("/ortools/generar")
    @Operation(
        summary = "Generate schedule using OR-Tools",
        description = "Generates an optimized schedule for workers using Google OR-Tools"
    )
    public ScheduleResponseDTO generateScheduleWithORTools(@Valid @RequestBody OrganizerRequestDTO request) {
        logger.info("Received OR-Tools schedule generation request");
        return organizerService.generateScheduleWithORTools(request);
    }

    @PostMapping("/save-schedule")
    @Operation(
        summary = "Save generated schedule",
        description = "Saves a generated schedule into the database"
    )
    public ResponseEntity<HorariosComun> saveSchedule(
            @Valid @RequestBody SaveScheduleRequestDTO scheduleData,
            @Valid @RequestBody OrganizerRequestDTO originalRequest) {
        logger.info("Received request to save schedule");
        HorariosComun savedSchedule = organizerService.saveSchedule(scheduleData, originalRequest);
        return ResponseEntity.ok(savedSchedule);
    }
}
