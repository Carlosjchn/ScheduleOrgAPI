package com.jpa1prueba.jpademo.dto.auth;

import com.jpa1prueba.jpademo.entities.enums.TipoUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String nombre;
    private String email;
    private String contrasena;
    private TipoUser tipo;
}
