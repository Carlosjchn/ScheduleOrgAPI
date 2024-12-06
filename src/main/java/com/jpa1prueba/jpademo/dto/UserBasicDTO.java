package com.jpa1prueba.jpademo.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;


// DTO for listing basic user info
@Builder
@Data
class UserBasicDTO {
    private long idUsuario;
    private String nombre;
    private String email;
}

// DTO for detailed user info, including team and schedules
@Builder
@Data
class UserDetailDTO {
    private long idUsuario;
    private String nombre;
    private String email;
    private EquipoDTO equipoAsociado;
    private List<HorarioBasicDTO> horariosUser;
}
