package com.jpa1prueba.jpademo.mappers;

import java.util.stream.Collectors;

import com.jpa1prueba.jpademo.dto.equipo.EquipoUsuarioDTO;
import com.jpa1prueba.jpademo.dto.user.UserBasicDTO;
import com.jpa1prueba.jpademo.dto.user.UserDetailDTO;
import com.jpa1prueba.jpademo.entities.Usuarios;

public class UserMapper {

    public static UserBasicDTO toUserBasicDTO(Usuarios user) {
        return UserBasicDTO.builder()
                .idUsuario(user.getIdUsuario())
                .nombre(user.getNombre())
                .email(user.getEmail())
                .tipo(user.getTipo())
                .build();
    }

    public static UserDetailDTO toUserDetailDTO(Usuarios user) {
        return UserDetailDTO.builder()
                .idUsuario(user.getIdUsuario())
                .nombre(user.getNombre())
                .email(user.getEmail())
                .tipo(user.getTipo())
                .equipos(user.getEquipos().stream()
                        .map(ue -> EquipoUsuarioDTO.builder()
                                .idEquipo(ue.getEquipo().getIdEquipo())
                                .nombreEquipo(ue.getEquipo().getNombre())
                                .rol(ue.getRol())
                                .build())
                        .collect(Collectors.toList()))
                .eventosUser(user.getEventos().stream()
                        .map(EventoMapper::toEventoDetailDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
