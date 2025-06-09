package com.jpa1prueba.jpademo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.jpa1prueba.jpademo.dto.horariocomun.HorarioComunDetailDTO;
import com.jpa1prueba.jpademo.entities.HorariosComun;
import com.jpa1prueba.jpademo.services.HorariosComunService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;

@RestController
@RequestMapping("api/horarios-comunes")
public class HorariosComunController {
    
    private final HorariosComunService horariosComunService;

    public HorariosComunController(HorariosComunService horariosComunService) {
        this.horariosComunService = horariosComunService;
    }

    @Operation(summary = "Obtener todos los horarios comunes")
    @ApiResponse(responseCode = "200", description = "Horarios comunes recuperados con éxito",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = HorarioComunDetailDTO.class)))
    @GetMapping("/all")
    public ResponseEntity<List<HorarioComunDetailDTO>> getAllHorariosComunes() {
        return ResponseEntity.ok(horariosComunService.getAllHorariosComunes());
    }

    @Operation(summary = "Obtener horarios comunes por equipo")
    @ApiResponse(responseCode = "200", description = "Horarios comunes del equipo recuperados con éxito")
    @GetMapping("/equipo/{idEquipo}")
    public ResponseEntity<List<HorarioComunDetailDTO>> getHorariosComunesByEquipo(@PathVariable Long idEquipo) {
        return ResponseEntity.ok(horariosComunService.getHorariosComunesByEquipo(idEquipo));
    }

    @Operation(summary = "Crear nuevo horario común")
    @ApiResponse(responseCode = "201", description = "Horario común creado con éxito")
    @PostMapping("/crear")
    public ResponseEntity<HorariosComun> createHorarioComun(@RequestBody HorariosComun horarioComun) {
        return new ResponseEntity<>(horariosComunService.createHorarioComun(horarioComun), HttpStatus.CREATED);
    }

    @Operation(summary = "Eliminar horario común")
    @ApiResponse(responseCode = "204", description = "Horario común eliminado con éxito")
    @DeleteMapping("/borrar/{idHorarioComun}")
    public ResponseEntity<Void> deleteHorarioComun(@PathVariable Long idHorarioComun) {
        horariosComunService.deleteHorarioComun(idHorarioComun);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar horario común")
    @ApiResponse(responseCode = "200", description = "Horario común actualizado con éxito")
    @PutMapping("/actualizar/{idHorarioComun}")
    public ResponseEntity<HorariosComun> updateHorarioComun(
            @PathVariable Long idHorarioComun,
            @RequestBody HorariosComun horarioComun) {
        return ResponseEntity.ok(horariosComunService.updateHorarioComun(idHorarioComun, horarioComun));
    }
}