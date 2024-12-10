package com.jpa1prueba.jpademo.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBasicDTO {
    // DTO for listing basic user info
    private String nombre;
    private String email;
}

