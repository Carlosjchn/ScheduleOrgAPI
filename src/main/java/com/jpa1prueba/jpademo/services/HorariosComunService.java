package com.jpa1prueba.jpademo.services;

import org.springframework.stereotype.Service;
import com.jpa1prueba.jpademo.dto.horariocomun.HorarioComunDetailDTO;
import com.jpa1prueba.jpademo.entities.HorariosComun;
import com.jpa1prueba.jpademo.entities.Equipos;
import com.jpa1prueba.jpademo.mappers.HorarioComunMapper;
import com.jpa1prueba.jpademo.repositories.HorariosComunRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HorariosComunService {

    private final HorariosComunRepository horariosComunRepository;
    private final EquiposService equiposService;

    public HorariosComunService(HorariosComunRepository horariosComunRepository, 
                               EquiposService equiposService) {
        this.horariosComunRepository = horariosComunRepository;
        this.equiposService = equiposService;
    }

    public List<HorarioComunDetailDTO> getAllHorariosComunes() {
        return horariosComunRepository.findAll()
                .stream()
                .map(HorarioComunMapper::toHorarioComunDetailDTO)
                .collect(Collectors.toList());
    }

    public List<HorarioComunDetailDTO> getHorariosComunesByEquipo(Long idEquipo) {
        Equipos equipo = equiposService.getEquipoById(idEquipo);
        return horariosComunRepository.findByEquipoAsociado(equipo)
                .stream()
                .map(HorarioComunMapper::toHorarioComunDetailDTO)
                .collect(Collectors.toList());
    }

    public HorariosComun createHorarioComun(HorariosComun horarioComun) {
        return horariosComunRepository.save(horarioComun);
    }

    public void deleteHorarioComun(Long idHorarioComun) {
        horariosComunRepository.deleteById(idHorarioComun);
    }

    public HorariosComun updateHorarioComun(Long idHorarioComun, HorariosComun horarioComun) {
        HorariosComun existingHorarioComun = horariosComunRepository.findById(idHorarioComun)
                .orElseThrow(() -> new RuntimeException("Horario común no encontrado"));

        existingHorarioComun.setNombre(horarioComun.getNombre());
        existingHorarioComun.setEquipoAsociado(horarioComun.getEquipoAsociado());

        return horariosComunRepository.save(existingHorarioComun);
    }
}