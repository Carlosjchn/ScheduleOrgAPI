package com.jpa1prueba.jpademo.mappers;

import java.util.stream.Collectors;

import com.jpa1prueba.jpademo.dto.equipo.EquipoBasicDTO;
import com.jpa1prueba.jpademo.dto.equipo.EquipoDetailDTO;
import com.jpa1prueba.jpademo.entities.Equipos;
import com.jpa1prueba.jpademo.entities.UsuarioEquipo;

public class EquipoMapper {

    public static EquipoBasicDTO toEquipoBasicDTO(Equipos equipo) {
        if (equipo == null) {
            return null; // Return null or a default DTO if equipo is null
        }
        return EquipoBasicDTO.builder()
                .nombre(equipo.getNombre())
                .tipo(equipo.getTipo())
                .build();
    }

    public static EquipoDetailDTO toEquipoDetailDTO(Equipos equipo) {
        return EquipoDetailDTO.builder()
                .idEquipo(equipo.getIdEquipo())
                .tipo(equipo.getTipo())
                .nombre(equipo.getNombre())
                .horaInicio(equipo.getHoraInicioAct())
                .horaFin(equipo.getHoraFinAct())
                .Usuarios(equipo.getUsuariosEquipo().stream()
                        .map(UsuarioEquipo::getUsuario)
                        .map(UserMapper::toUserBasicDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
