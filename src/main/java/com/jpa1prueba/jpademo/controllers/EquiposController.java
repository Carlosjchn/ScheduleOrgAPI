package com.jpa1prueba.jpademo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jpa1prueba.jpademo.entities.Equipos;
import com.jpa1prueba.jpademo.services.EquiposService;

@RestController
@RequestMapping("/api/equipos")
public class EquiposController {

    @Autowired
    private EquiposService equiposService;

    @GetMapping("/{idEquipo}")
    public Equipos getEquipo(@PathVariable Long idEquipo) {
        return equiposService.getEquipoById(idEquipo);
    }

    @PostMapping
    public Equipos createEquipo(@RequestBody Equipos equipo) {
        return equiposService.createEquipo(equipo);
    }

    @DeleteMapping("/{idEquipo}")
    public void deleteEquipo(@PathVariable Long idEquipo) {
        equiposService.deleteEquipo(idEquipo);
    }
}

