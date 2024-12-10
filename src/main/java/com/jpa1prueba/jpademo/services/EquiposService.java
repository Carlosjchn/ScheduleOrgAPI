package com.jpa1prueba.jpademo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa1prueba.jpademo.entities.Equipos;
import com.jpa1prueba.jpademo.repositories.EquiposRepository;

@Service
public class EquiposService {

    @Autowired
    private EquiposRepository equiposRepository;

    public Equipos getEquipoById(Long idEquipo) {
        return equiposRepository.findById(idEquipo).orElse(null);
    }

    public Equipos createEquipo(Equipos equipo) {
        return equiposRepository.save(equipo);
    }

    public void deleteEquipo(Long idEquipo) {
        equiposRepository.deleteById(idEquipo);
    }
}

