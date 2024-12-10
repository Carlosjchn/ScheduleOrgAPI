package com.jpa1prueba.jpademo.dto.equipo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipoBasicDTO {

    private String nombre;
    private String tipo;

}
