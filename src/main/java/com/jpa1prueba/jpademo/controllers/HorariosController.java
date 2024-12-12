package com.jpa1prueba.jpademo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpa1prueba.jpademo.dto.horario.HorarioDetailDTO;
import com.jpa1prueba.jpademo.entities.Horarios;
import com.jpa1prueba.jpademo.services.HorariosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;
import java.util.Optional;

/**
 * Controlador para gestionar los horarios en el sistema.
 */
@RestController
@RequestMapping("api/horarios")
public class HorariosController {

    @Autowired
    private HorariosService horariosService;

    /**
     * Obtiene todos los horarios registrados en el sistema.
     * 
     * @return Una lista de objetos `HorarioDetailDTO` que contienen los detalles de
     *         todos los horarios.
     */
    @Operation(summary = "Obtener todos los horarios", description = "Recupera todos los horarios registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Horarios recuperados con éxito.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = HorarioDetailDTO.class)))
    @GetMapping("/all")
    public ResponseEntity<List<HorarioDetailDTO>> getAllHorarios() {
        List<HorarioDetailDTO> horarios = horariosService.getAllHorarios();
        return ResponseEntity.ok(horarios);
    }

    /**
     * Obtiene los horarios asociados a un usuario específico.
     * 
     * @param idUsuario ID del usuario para el cual se recuperan los horarios.
     * @return Una lista de objetos `HorarioDetailDTO` con los horarios del usuario.
     */
    @Operation(summary = "Obtener horarios por usuario", description = "Recupera los horarios asociados a un usuario específico.")
    @ApiResponse(responseCode = "200", description = "Horarios recuperados con éxito.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = HorarioDetailDTO.class)))
    @GetMapping("/idUsuario/{idUsuario}")
    public ResponseEntity<List<HorarioDetailDTO>> getHorariosByUsuario(@PathVariable Long idUsuario) {
        List<HorarioDetailDTO> horarios = horariosService.getHorariosByIdUsuario(idUsuario);
        return ResponseEntity.ok(horarios);
    }

    /**
     * Obtiene un horario específico de un usuario para una fecha dada.
     * 
     * @param idUsuario ID del usuario.
     * @param fecha     Fecha en formato "yyyy-MM-dd" para la cual se quiere obtener
     *                  el horario.
     * @return Un objeto `HorarioDetailDTO` con el horario del usuario en esa fecha,
     *         o vacío si no existe.
     */
    @Operation(summary = "Obtener horario por usuario y fecha", description = "Recupera el horario de un usuario en una fecha específica.")
    @ApiResponse(responseCode = "200", description = "Horario recuperado con éxito o vacío si no se encuentra.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = HorarioDetailDTO.class)))
    @GetMapping("/idUsuario/{idUsuario}/fecha/{fecha}")
    public ResponseEntity<Optional<HorarioDetailDTO>> getHorarioByIdUsuarioAndFecha(@PathVariable Long idUsuario,
            @PathVariable String fecha) {
        Optional<HorarioDetailDTO> horario = horariosService.getHorarioByIdUsuarioAndFecha(idUsuario, fecha);
        return ResponseEntity.ok(horario);
    }

    /**
     * Obtiene los horarios de un usuario para un mes específico.
     * 
     * @param idUsuario ID del usuario.
     * @param month     Mes (1-12) para el cual se quiere obtener los horarios.
     * @return Una lista de objetos `HorarioDetailDTO` con los horarios del usuario
     *         en ese mes.
     */
    @Operation(summary = "Obtener horarios por usuario y mes", description = "Recupera los horarios de un usuario para un mes específico.")
    @ApiResponse(responseCode = "200", description = "Horarios recuperados con éxito.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = HorarioDetailDTO.class)))
    @GetMapping("/idUsuario/{idUsuario}/month/{month}")
    public ResponseEntity<List<HorarioDetailDTO>> getHorariosByUsuarioAndMonth(@PathVariable Long idUsuario,
            @PathVariable int month) {
        List<HorarioDetailDTO> horarios = horariosService.getHorariosByIdUsuarioAndMonth(idUsuario, month);
        return ResponseEntity.ok(horarios);
    }

    /**
     * Crea un nuevo horario en el sistema.
     * 
     * @param horario Objeto `Horarios` que contiene los detalles del nuevo horario
     *                a crear.
     * @return El horario recién creado con todos los detalles.
     */
    @Operation(summary = "Crear nuevo horario", description = "Permite crear un nuevo horario en el sistema.")
    @ApiResponse(responseCode = "201", description = "Horario creado con éxito.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Horarios.class)))
    @PostMapping("/crear")
    public ResponseEntity<Horarios> createHorario(@RequestBody Horarios horario) {
        Horarios createdHorario = horariosService.createHorario(horario);
        return new ResponseEntity<>(createdHorario, HttpStatus.CREATED);
    }

    /**
     * Elimina un horario del sistema.
     * 
     * @param idHorario ID del horario a eliminar.
     * @return Respuesta vacía con código HTTP 204.
     */
    @Operation(summary = "Eliminar horario", description = "Permite eliminar un horario del sistema.")
    @ApiResponse(responseCode = "204", description = "Horario eliminado con éxito.")
    @DeleteMapping("/borrar/{idHorario}")
    public ResponseEntity<Void> deleteHorario(@PathVariable Long idHorario) {
        horariosService.deleteHorario(idHorario);
        return ResponseEntity.noContent().build();
    }

    /**
     * Actualiza un horario existente.
     * 
     * @param idHorario ID del horario a actualizar.
     * @param horario   Objeto `Horarios` que contiene los nuevos detalles del
     *                  horario.
     * @return El horario actualizado.
     */
    @Operation(summary = "Actualizar horario", description = "Permite actualizar un horario existente en el sistema.")
    @ApiResponse(responseCode = "200", description = "Horario actualizado con éxito.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Horarios.class)))
    @PutMapping("/actualizar/{idHorario}")
    public ResponseEntity<Horarios> updateHorario(@PathVariable Long idHorario, @RequestBody Horarios horario) {
        Horarios updatedHorario = horariosService.updateHorario(idHorario, horario);
        return ResponseEntity.ok(updatedHorario);
    }

}
