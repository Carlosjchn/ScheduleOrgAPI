package com.jpa1prueba.jpademo.dto.equipo;

import com.jpa1prueba.jpademo.entities.enums.RolEquipo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipoUsuarioDTO {
    private Long idEquipo;
    private String nombreEquipo;
    private RolEquipo rol;
}