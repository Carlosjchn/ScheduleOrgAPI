package com.jpa1prueba.jpademo.dto.user;

import com.jpa1prueba.jpademo.entities.enums.TipoUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBasicDTO {
    private Long idUsuario;
    private String nombre;
    private String email;
    private TipoUser tipo;
}

