package com.jpa1prueba.jpademo.dto.evento;

import java.time.LocalDate;
import java.time.LocalTime;

import com.jpa1prueba.jpademo.dto.user.UserBasicDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoDetailDTO {
    private Long idEvento;
    private String titulo;
    private UserBasicDTO usuarioAsociado;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
}