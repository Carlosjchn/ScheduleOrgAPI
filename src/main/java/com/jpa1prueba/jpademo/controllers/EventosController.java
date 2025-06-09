package com.jpa1prueba.jpademo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpa1prueba.jpademo.dto.evento.CreateEventoRequest;
import com.jpa1prueba.jpademo.dto.evento.EventoDetailDTO;
import com.jpa1prueba.jpademo.entities.Eventos;
import com.jpa1prueba.jpademo.mappers.EventoMapper;
import com.jpa1prueba.jpademo.services.EventosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/eventos")
public class EventosController {
    
    private final EventosService eventosService;

    public EventosController(EventosService eventosService) {
        this.eventosService = eventosService;
    }

    @Operation(summary = "Obtener todos los eventos", description = "Recupera todos los eventos registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Eventos recuperados con éxito.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoDetailDTO.class)))
    @GetMapping("/all")
    public ResponseEntity<List<EventoDetailDTO>> getAllEventos() {
        List<EventoDetailDTO> eventos = eventosService.getAllEventos();
        return ResponseEntity.ok(eventos);
    }

    @Operation(summary = "Obtener eventos por usuario", description = "Recupera los eventos asociados a un usuario específico.")
    @ApiResponse(responseCode = "200", description = "Eventos recuperados con éxito.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoDetailDTO.class)))
    @GetMapping("/idUsuario/{idUsuario}")
    public ResponseEntity<List<EventoDetailDTO>> getEventosByUsuario(@PathVariable Long idUsuario) {
        List<EventoDetailDTO> eventos = eventosService.getEventosByIdUsuario(idUsuario);
        return ResponseEntity.ok(eventos);
    }

    @Operation(summary = "Obtener evento por usuario y fecha", description = "Recupera el evento de un usuario en una fecha específica.")
    @ApiResponse(responseCode = "200", description = "Evento recuperado con éxito o vacío si no se encuentra.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoDetailDTO.class)))
    @GetMapping("/idUsuario/{idUsuario}/fecha/{fecha}")
    public ResponseEntity<Optional<EventoDetailDTO>> getEventoByIdUsuarioAndFecha(@PathVariable Long idUsuario,
            @PathVariable String fecha) {
        Optional<EventoDetailDTO> evento = eventosService.getEventoByIdUsuarioAndFecha(idUsuario, fecha);
        return ResponseEntity.ok(evento);
    }

    @Operation(summary = "Obtener eventos por usuario y mes", description = "Recupera los eventos de un usuario para un mes específico.")
    @ApiResponse(responseCode = "200", description = "Eventos recuperados con éxito.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoDetailDTO.class)))
    @GetMapping("/idUsuario/{idUsuario}/month/{month}")
    public ResponseEntity<List<EventoDetailDTO>> getEventosByUsuarioAndMonth(@PathVariable Long idUsuario,
            @PathVariable int month) {
        List<EventoDetailDTO> eventos = eventosService.getEventosByIdUsuarioAndMonth(idUsuario, month);
        return ResponseEntity.ok(eventos);
    }

    @Operation(summary = "Crear nuevo evento", description = "Permite crear un nuevo evento con información mínima requerida.")
    @ApiResponse(responseCode = "201", description = "Evento creado con éxito.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EventoDetailDTO.class)))
    @PostMapping("/crear")
    public ResponseEntity<EventoDetailDTO> createEvento(@RequestBody CreateEventoRequest request) {
        try {
            Eventos createdEvento = eventosService.createEvento(request);
            EventoDetailDTO eventoDTO = EventoMapper.toEventoDetailDTO(createdEvento);
            return new ResponseEntity<>(eventoDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @Operation(summary = "Eliminar evento", description = "Permite eliminar un evento del sistema.")
    @ApiResponse(responseCode = "204", description = "Evento eliminado con éxito.")
    @DeleteMapping("/borrar/{idEvento}")
    public ResponseEntity<Void> deleteEvento(@PathVariable Long idEvento) {
        eventosService.deleteEvento(idEvento);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar evento", description = "Permite actualizar un evento existente en el sistema.")
    @ApiResponse(responseCode = "200", description = "Evento actualizado con éxito.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Eventos.class)))
    @PutMapping("/actualizar/{idEvento}")
    public ResponseEntity<Eventos> updateEvento(@PathVariable Long idEvento, @RequestBody Eventos evento) {
        Eventos updatedEvento = eventosService.updateEvento(idEvento, evento);
        return ResponseEntity.ok(updatedEvento);
    }
}