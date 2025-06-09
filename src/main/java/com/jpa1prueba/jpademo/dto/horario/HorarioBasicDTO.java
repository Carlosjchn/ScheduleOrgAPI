package com.jpa1prueba.jpademo.dto.horario;

import java.util.List;

import com.jpa1prueba.jpademo.dto.equipo.EquipoBasicDTO;
import com.jpa1prueba.jpademo.dto.evento.EventoDetailDTO;
import com.jpa1prueba.jpademo.dto.user.UserBasicDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorarioBasicDTO {
    private Long idHorario;
    private String nombre;
    private UserBasicDTO usuarioAsociado;
    private List<EventoDetailDTO> eventos;
}
