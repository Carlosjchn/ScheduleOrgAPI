package com.jpa1prueba.jpademo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jpa1prueba.jpademo.dto.equipo.EquipoBasicDTO;
import com.jpa1prueba.jpademo.dto.equipo.EquipoDetailDTO;
import com.jpa1prueba.jpademo.entities.Equipos;

import com.jpa1prueba.jpademo.services.EquiposService;

@RestController
@RequestMapping("/api/equipos")
public class EquiposController {

    @Autowired
    private EquiposService equiposService;

    @GetMapping("/all")
    public List<EquipoBasicDTO> getAllEquipos() {
        return equiposService.getAllEquipos();
        
    }

    @GetMapping("/nombre/{nombre}")
    public EquipoDetailDTO getByNombre(@PathVariable String nombre) {
        return equiposService.getByNombre(nombre);
    }

    @GetMapping("/id/{idEquipo}")
    public Equipos getEquipo(@PathVariable Long idEquipo) {
        return equiposService.getEquipoById(idEquipo);
    }

    @PostMapping("/crear")
    public Equipos createEquipo(@RequestBody Equipos equipo) {
        return equiposService.createEquipo(equipo);
    }

    @DeleteMapping("/borrar/{idEquipo}")
    public void deleteEquipo(@PathVariable Long idEquipo) {
        equiposService.deleteEquipo(idEquipo);
    }
}

