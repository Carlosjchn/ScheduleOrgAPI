package com.jpa1prueba.jpademo.dto.equipo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.jpa1prueba.jpademo.dto.user.UserBasicDTO;
import com.jpa1prueba.jpademo.entities.Usuarios;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipoDetailDTO {
    private Long idEquipo;
    private String tipo;
    private String nombre;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private List<UserBasicDTO> Usuarios;
}
