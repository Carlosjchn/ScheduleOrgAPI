package com.jpa1prueba.jpademo.dto.horariocomun;

import com.jpa1prueba.jpademo.dto.equipo.EquipoBasicDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HorarioComunBasicDTO {
    private String nombre;
    private EquipoBasicDTO equipoAsociado;
}