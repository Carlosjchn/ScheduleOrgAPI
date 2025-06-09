package com.jpa1prueba.jpademo.mappers;

import java.util.ArrayList;
import java.util.List;

import com.jpa1prueba.jpademo.dto.horario.HorarioBasicDTO;
import com.jpa1prueba.jpademo.dto.horario.HorarioDetailDTO;
import com.jpa1prueba.jpademo.entities.Horarios;

public class HorarioMapper {

    public static HorarioBasicDTO toHorarioBasicDTO(Horarios horario) {
        return HorarioBasicDTO.builder()
                .idHorario(horario.getIdHorario())
                .nombre(horario.getNombre())
                .usuarioAsociado(UserMapper.toUserBasicDTO(horario.getUsuarioAsociado()))
                .eventos(EventoMapper.ListtoEventoDetailDTO(horario.getEventos()))
                .build();
    }

    public static HorarioDetailDTO toHorarioDetailDTO(Horarios horario) {
        return HorarioDetailDTO.builder()
                .idHorario(horario.getIdHorario())
                .nombre(horario.getNombre())
                .usuarioAsociado(UserMapper.toUserBasicDTO(horario.getUsuarioAsociado()))
                .eventos(EventoMapper.ListtoEventoDetailDTO(horario.getEventos()))
                .build();
    }

    public static List<HorarioDetailDTO> ListtoHorarioDetailDTO(List<Horarios> horarios) {
        List<HorarioDetailDTO> listaHorarios = new ArrayList<>();
        for (Horarios horario : horarios) {
            listaHorarios.add(toHorarioDetailDTO(horario));
        }
        return listaHorarios;
    }

}
