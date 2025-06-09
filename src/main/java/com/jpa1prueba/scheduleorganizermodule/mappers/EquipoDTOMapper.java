package com.jpa1prueba.scheduleorganizermodule.mappers;

import com.jpa1prueba.jpademo.entities.Equipos;
import com.jpa1prueba.scheduleorganizermodule.dtos.EquipoDTO;
import org.springframework.stereotype.Component;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class EquipoDTOMapper {
    
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    
    public EquipoDTO toDTO(Equipos equipo) {
        return EquipoDTO.builder()
                .idEquipo(equipo.getIdEquipo())
                .tipo(equipo.getTipo())
                .nombre(equipo.getNombre())
                .diasActividad("") // Este campo no existe en la entidad
                .horaInicioActividad(equipo.getHoraInicioAct().format(TIME_FORMATTER))
                .horaFinActividad(equipo.getHoraFinAct().format(TIME_FORMATTER))
                .horasMinDiaria(8) // Valor por defecto
                .horasMaxDiaria(12) // Valor por defecto
                .build();
    }

    public Equipos toEntity(EquipoDTO dto) {
        Equipos equipo = new Equipos();
        equipo.setIdEquipo(dto.getIdEquipo());
        equipo.setTipo(dto.getTipo());
        equipo.setNombre(dto.getNombre());
        equipo.setHoraInicioAct(LocalTime.parse(dto.getHoraInicioActividad(), TIME_FORMATTER));
        equipo.setHoraFinAct(LocalTime.parse(dto.getHoraFinActividad(), TIME_FORMATTER));
        return equipo;
    }
} 