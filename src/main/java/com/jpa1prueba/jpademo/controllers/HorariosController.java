package com.jpa1prueba.jpademo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jpa1prueba.jpademo.entities.Horarios;
import com.jpa1prueba.jpademo.entities.Usuarios;
import com.jpa1prueba.jpademo.services.HorariosService;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")
public class HorariosController {

    @Autowired
    private HorariosService horariosService;

    @GetMapping("/{idUsuario}")
    public List<Horarios> getHorariosByUsuario(@PathVariable Usuarios isUsuario) {
        return horariosService.getHorariosByUsuario(isUsuario);
    }

    @PostMapping
    public Horarios createHorario(@RequestBody Horarios horario) {
        return horariosService.createHorario(horario);
    }

    @DeleteMapping("/{idHorario}")
    public void deleteHorario(@PathVariable Long idHorario) {
        horariosService.deleteHorario(idHorario);
    }
}

