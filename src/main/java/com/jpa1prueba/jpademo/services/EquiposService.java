package com.jpa1prueba.jpademo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jpa1prueba.jpademo.dto.equipo.EquipoBasicDTO;
import com.jpa1prueba.jpademo.dto.equipo.EquipoDetailDTO;
import com.jpa1prueba.jpademo.entities.Equipos;
import com.jpa1prueba.jpademo.mappers.EquipoMapper;
import com.jpa1prueba.jpademo.repositories.EquiposRepository;

@Service
public class EquiposService {

    private final EquiposRepository equiposRepository;
    
    public EquiposService(EquiposRepository equiposRepository){
        this.equiposRepository = equiposRepository;
    }

    public List<EquipoBasicDTO> getAllEquipos() {
        return equiposRepository.findAll()
                .stream()
                .map(EquipoMapper::toEquipoBasicDTO)
                .toList();
    }

    public EquipoDetailDTO getByNombre(String nombre) {
        return equiposRepository.findByNombre(nombre)
                .map(EquipoMapper::toEquipoDetailDTO)
                .orElseThrow(() -> new RuntimeException("No se encontró el equipo con el nombre"));
    }

    public Equipos getEquipoById(Long idEquipo) {
        return equiposRepository.findById(idEquipo).orElse(null);
    }

    public Equipos createEquipo(Equipos equipo) {
        return equiposRepository.save(equipo);
    }

    public void deleteEquipo(Long idEquipo) {
        equiposRepository.deleteById(idEquipo);
    }

    public Equipos updateEquipo(Long idEquipo, Equipos equipo) {
        // Buscar el equipo existente por su ID
        Equipos existingEquipo = equiposRepository.findById(idEquipo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
    
        // Actualizar los campos del equipo existente con los nuevos valores
        existingEquipo.setNombre(equipo.getNombre());
        existingEquipo.setTipo(equipo.getTipo());
        existingEquipo.setHoraInicioAct(equipo.getHoraInicioAct());
        existingEquipo.setHoraFinAct(equipo.getHoraFinAct());
    
        // Guardar el equipo actualizado
        return equiposRepository.save(existingEquipo);
    }
    
}
