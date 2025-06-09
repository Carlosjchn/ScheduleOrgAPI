package com.jpa1prueba.jpademo.dto.evento;

import java.time.LocalDate;

import com.jpa1prueba.jpademo.dto.user.UserBasicDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventoBasicDTO {
    private UserBasicDTO usuarioAsociado;
    private String titulo;
    private LocalDate fecha;
}