package com.jpa1prueba.jpademo.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpa1prueba.jpademo.dto.horario.HorarioBasicDTO;
import com.jpa1prueba.jpademo.dto.horario.HorarioDetailDTO;
import com.jpa1prueba.jpademo.entities.Horarios;
import com.jpa1prueba.jpademo.services.HorariosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;


/**
 * Controlador para gestionar los horarios en el sistema.
 */
@RestController
@RequestMapping("api/horarios")
public class HorariosController {

    
    private final HorariosService horariosService;

    public HorariosController(HorariosService horariosService) {
        this.horariosService = horariosService;
    }

    @Operation(summary = "Obtener todos los horarios")
    @ApiResponse(responseCode = "200", description = "Horarios recuperados con éxito", 
                content = @Content(mediaType = "application/json", 
                schema = @Schema(implementation = HorarioDetailDTO.class)))
    @GetMapping("/all")
    public ResponseEntity<List<HorarioBasicDTO>> getAllHorarios() {
        return ResponseEntity.ok(horariosService.getAllHorarios());
    }

    @Operation(summary = "Obtener horarios por usuario")
    @ApiResponse(responseCode = "200", description = "Horarios del usuario recuperados con éxito")
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<HorarioDetailDTO>> getHorariosByUsuario(@PathVariable Long idUsuario) {
        return ResponseEntity.ok(horariosService.getHorariosByIdUsuario(idUsuario));
    }

    @Operation(summary = "Crear nuevo horario")
    @ApiResponse(responseCode = "201", description = "Horario creado con éxito")
    @PostMapping("/crear")
    public ResponseEntity<Horarios> createHorario(@RequestBody Horarios horario) {
        return new ResponseEntity<>(horariosService.createHorario(horario), HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminar horario")
    @ApiResponse(responseCode = "204", description = "Horario eliminado con éxito")
    @DeleteMapping("/borrar/{idHorario}")
    public ResponseEntity<Void> deleteHorario(@PathVariable Long idHorario) {
        horariosService.deleteHorario(idHorario);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar horario")
    @ApiResponse(responseCode = "200", description = "Horario actualizado con éxito")
    @PutMapping("/actualizar/{idHorario}")
    public ResponseEntity<Horarios> updateHorario(@PathVariable Long idHorario, @RequestBody Horarios horario) {
        return ResponseEntity.ok(horariosService.updateHorario(idHorario, horario));
    }

    @Operation(summary = "Obtener horario por nombre o ID")
    @ApiResponse(responseCode = "200", description = "Horario recuperado con éxito",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = HorarioDetailDTO.class)))
    @GetMapping("/buscar/{nombreOrId}")
    public ResponseEntity<HorarioDetailDTO> getHorarioByNombreOrId(@PathVariable String nombreOrId) {
        try {
            HorarioDetailDTO horario = horariosService.getHorarioByNombreOrId(nombreOrId);
            return ResponseEntity.ok(horario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
