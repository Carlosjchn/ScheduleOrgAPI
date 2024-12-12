package com.jpa1prueba.jpademo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpa1prueba.jpademo.dto.horario.HorarioDetailDTO;
import com.jpa1prueba.jpademo.entities.Horarios;
import com.jpa1prueba.jpademo.services.HorariosService;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")
public class HorariosController {

    @Autowired
    private HorariosService horariosService;

    @GetMapping("/all")
    public ResponseEntity<List<HorarioDetailDTO>> getAllHorarios() {
        List<HorarioDetailDTO> horarios = horariosService.getAllHorarios();
        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/idUsuario/{idUsuario}")
    public ResponseEntity<List<HorarioDetailDTO>> getHorariosByUsuario(@PathVariable Long idUsuario) {
        List<HorarioDetailDTO> horarios = horariosService.getHorariosByIdUsuario(idUsuario);
        return ResponseEntity.ok(horarios);
    }

    @PostMapping("/crear")
    public ResponseEntity<Horarios> createHorario(@RequestBody Horarios horario) {
        Horarios createdHorario = horariosService.createHorario(horario);
        return new ResponseEntity<>(createdHorario, HttpStatus.CREATED);
    }

    @DeleteMapping("/borrar/{idHorario}")
    public ResponseEntity<Void> deleteHorario(@PathVariable Long idHorario) {
        horariosService.deleteHorario(idHorario);
        return ResponseEntity.noContent().build();
    }
}

