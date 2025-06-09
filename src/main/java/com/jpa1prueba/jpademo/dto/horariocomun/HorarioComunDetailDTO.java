package com.jpa1prueba.jpademo.dto.horariocomun;

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
public class HorarioComunDetailDTO {
    private Long idHorarioComun;
    private String nombre;
    private EquipoBasicDTO equipoAsociado;
    private List<HorarioDetailDTO> horarios;
}