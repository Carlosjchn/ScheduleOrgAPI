package com.jpa1prueba.jpademo.dto.user;

import java.util.List;

import com.jpa1prueba.jpademo.dto.equipo.EquipoUsuarioDTO;
import com.jpa1prueba.jpademo.dto.evento.EventoDetailDTO;
import com.jpa1prueba.jpademo.entities.enums.TipoUser;

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
    private TipoUser tipo;
    private List<EquipoUsuarioDTO> equipos;
    private List<EventoDetailDTO> eventosUser;
}
