package com.jpa1prueba.jpademo.mappers;

import java.util.ArrayList;
import java.util.List;

import com.jpa1prueba.jpademo.dto.evento.EventoBasicDTO;
import com.jpa1prueba.jpademo.dto.evento.EventoDetailDTO;
import com.jpa1prueba.jpademo.entities.Eventos;

public class EventoMapper {

    public static EventoBasicDTO toEventoBasicDTO(Eventos evento) {
        return EventoBasicDTO.builder()
                .usuarioAsociado(UserMapper.toUserBasicDTO(evento.getUsuarioAsociado()))
                .titulo(evento.getTitulo())
                .fecha(evento.getFecha())
                .build();
    }

    public static EventoDetailDTO toEventoDetailDTO(Eventos evento) {
        return EventoDetailDTO.builder()
                .idEvento(evento.getIdEvento())
                .usuarioAsociado(UserMapper.toUserBasicDTO(evento.getUsuarioAsociado()))
                .titulo(evento.getTitulo())
                .fecha(evento.getFecha())
                .horaInicio(evento.getHoraInicio())
                .horaFin(evento.getHoraFin())
                .build();
    }

    public static List<EventoDetailDTO> ListtoEventoDetailDTO(List<Eventos> eventos) {
        List<EventoDetailDTO> listaEventos = new ArrayList<>();
        for (Eventos evento : eventos) {
            listaEventos.add(EventoDetailDTO.builder()
                    .idEvento(evento.getIdEvento())
                    .usuarioAsociado(UserMapper.toUserBasicDTO(evento.getUsuarioAsociado()))
                    .titulo(evento.getTitulo())
                    .fecha(evento.getFecha())
                    .horaInicio(evento.getHoraInicio())
                    .horaFin(evento.getHoraFin())
                    .build());
        }
        return listaEventos;
    }
}