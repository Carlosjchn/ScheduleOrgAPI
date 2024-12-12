package com.jpa1prueba.jpademo.dto.user;

import java.util.List;

import com.jpa1prueba.jpademo.dto.equipo.EquipoBasicDTO;
import com.jpa1prueba.jpademo.dto.horario.HorarioDetailDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailDTO {
    private Long idUsuario;
    private String nombre;
    private String email;
    private String contrasena;
    private EquipoBasicDTO equipoAsociado;
    private List<HorarioDetailDTO> horariosUser;
}
