package com.jpa1prueba.jpademo.mappers;

import java.util.stream.Collectors;

import com.jpa1prueba.jpademo.dto.user.UserBasicDTO;
import com.jpa1prueba.jpademo.dto.user.UserDetailDTO;
import com.jpa1prueba.jpademo.entities.Usuarios;

public class UserMapper {

    public static UserBasicDTO toUserBasicDTO(Usuarios user) {
        return UserBasicDTO.builder()
                .nombre(user.getNombre())
                .email(user.getEmail())
                .build();
    }

    public static UserDetailDTO toUserDetailDTO(Usuarios user) {
        return UserDetailDTO.builder()
                .idUsuario(user.getIdUsuario())
                .nombre(user.getNombre())
                .email(user.getEmail())
                .contrasena(user.getContrasena())
                .equipoAsociado(EquipoMapper.toEquipoBasicDTO(user.getEquipoUser()))
                .horariosUser(user.getHorarios().stream()
                        .map(HorarioMapper::toHorarioDetailDTO)
                        .collect(Collectors.toList()))
                .build();
    }
}
