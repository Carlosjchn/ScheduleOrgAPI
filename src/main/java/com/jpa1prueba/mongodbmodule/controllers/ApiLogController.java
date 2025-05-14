package com.jpa1prueba.mongodbmodule.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa1prueba.mongodbmodule.entities.ApiLog;
import com.jpa1prueba.mongodbmodule.services.ApiLogService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/logs")
public class ApiLogController {

    private final ApiLogService apiLogService;

    public ApiLogController(ApiLogService apiLogService) {
        this.apiLogService = apiLogService;
    }

    @Operation(summary = "Obtener todos los logs de API", description = "Recupera todos los registros de llamadas a la API de la base de datos")
    @ApiResponse(responseCode = "200", description = "Logs recuperados exitosamente", 
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ApiLog.class)))
    @GetMapping("/all")
    public ResponseEntity<List<ApiLog>> getAllLogs() {
        return ResponseEntity.ok(apiLogService.getAllLogs());
    }

    @Operation(summary = "Borrar todos los logs de API", description = "Elimina todos los registros de llamadas a la API de la base de datos")
    @ApiResponse(responseCode = "200", description = "Logs eliminados exitosamente")
    @DeleteMapping("/clear")
    public ResponseEntity<String> clearAllLogs() {
        apiLogService.clearAllLogs();
        return ResponseEntity.ok("Todos los logs han sido eliminados");
    }
}