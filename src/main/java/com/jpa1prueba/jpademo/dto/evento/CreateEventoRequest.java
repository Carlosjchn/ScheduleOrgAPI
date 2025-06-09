package com.jpa1prueba.jpademo.dto.evento;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventoRequest {
    private String titulo;
    private Long idUsuario;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
} 