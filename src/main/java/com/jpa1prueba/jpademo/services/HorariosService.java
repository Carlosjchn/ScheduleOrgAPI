package com.jpa1prueba.jpademo.services;

import org.springframework.stereotype.Service;

import com.jpa1prueba.jpademo.dto.horario.HorarioDetailDTO;
import com.jpa1prueba.jpademo.dto.horario.HorarioBasicDTO;
import com.jpa1prueba.jpademo.entities.Horarios;
import com.jpa1prueba.jpademo.entities.Usuarios;
import com.jpa1prueba.jpademo.mappers.HorarioMapper;
import com.jpa1prueba.jpademo.repositories.HorariosRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class HorariosService {

    private final HorariosRepository horariosRepository;
    private final UsuarioService usuarioService;

    public HorariosService(HorariosRepository horariosRepository, UsuarioService usuarioService) {
        this.horariosRepository = horariosRepository;
        this.usuarioService = usuarioService;
    }

    public List<HorarioBasicDTO> getAllHorarios() {
        return horariosRepository.findAll()
                .stream()
                .map(HorarioMapper::toHorarioBasicDTO)
                .collect(Collectors.toList());
    }

    public List<HorarioDetailDTO> getHorariosByIdUsuario(Long idUsuario) {
        Usuarios usuario = usuarioService.getUsuarioById(idUsuario);
        return horariosRepository.findByUsuarioAsociado(usuario)
                .stream()
                .map(HorarioMapper::toHorarioDetailDTO)
                .collect(Collectors.toList());
    }

    public HorarioDetailDTO getHorarioByNombreOrId(String nombreOrId) {
        try {
            // Try to parse as ID first
            Long id = Long.parseLong(nombreOrId);
            return horariosRepository.findById(id)
                    .map(HorarioMapper::toHorarioDetailDTO)
                    .orElseThrow(() -> new RuntimeException("Horario no encontrado con ID: " + id));
        } catch (NumberFormatException e) {
            // If not a number, search by name
            return horariosRepository.findByNombre(nombreOrId)
                    .map(HorarioMapper::toHorarioDetailDTO)
                    .orElseThrow(() -> new RuntimeException("Horario no encontrado con nombre: " + nombreOrId));
        }
    }

    public Horarios createHorario(Horarios horario) {
        return horariosRepository.save(horario);
    }

    public void deleteHorario(Long idHorario) {
        horariosRepository.deleteById(idHorario);
    }

    public Horarios updateHorario(Long idHorario, Horarios horario) {
        Horarios existingHorario = horariosRepository.findById(idHorario)
                .orElseThrow(() -> new RuntimeException("Horario no encontrado"));

        existingHorario.setNombre(horario.getNombre());
        existingHorario.setUsuarioAsociado(horario.getUsuarioAsociado());

        return horariosRepository.save(existingHorario);
    }
}
