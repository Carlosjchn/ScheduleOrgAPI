package com.jpa1prueba.jpademo.mappers;

import java.util.ArrayList;
import java.util.List;

import com.jpa1prueba.jpademo.dto.horario.HorarioBasicDTO;
import com.jpa1prueba.jpademo.dto.horario.HorarioDetailDTO;
import com.jpa1prueba.jpademo.entities.Horarios;

public class HorarioMapper {

    public static HorarioBasicDTO toHorarioBasicDTO(Horarios horario) {
        return HorarioBasicDTO.builder()
                .usuarioAsociado(UserMapper.toUserBasicDTO(horario.getUsuarioAsociado()))
                .fecha(horario.getFecha())
                .build();
    }

    public static HorarioDetailDTO toHorarioDetailDTO(Horarios horario) {
        return HorarioDetailDTO.builder()
                .idHorario(horario.getIdHorario())
                .usuarioAsociado(UserMapper.toUserBasicDTO(horario.getUsuarioAsociado()))
                .fecha(horario.getFecha())
                .horaInicio(horario.getHoraInicio())
                .horaFin(horario.getHoraFin())
                .build();
    }

    public static List<HorarioDetailDTO> ListtoHorarioDetailDTO(List<Horarios> horarios) {
        List<HorarioDetailDTO> listaHorarios = new ArrayList<>();
        for (Horarios horario : horarios) {
        listaHorarios.add(HorarioDetailDTO.builder()
                .idHorario(horario.getIdHorario())
                .usuarioAsociado(UserMapper.toUserBasicDTO(horario.getUsuarioAsociado()))
                .fecha(horario.getFecha())
                .horaInicio(horario.getHoraInicio())
                .horaFin(horario.getHoraFin())
                .build());
            }
        return listaHorarios;
    }
}
