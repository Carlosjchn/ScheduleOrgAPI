package com.jpa1prueba.jpademo.mappers;

import java.util.ArrayList;
import java.util.List;
import com.jpa1prueba.jpademo.dto.horariocomun.HorarioComunBasicDTO;
import com.jpa1prueba.jpademo.dto.horariocomun.HorarioComunDetailDTO;
import com.jpa1prueba.jpademo.entities.HorariosComun;

public class HorarioComunMapper {
    
    public static HorarioComunBasicDTO toHorarioComunBasicDTO(HorariosComun horarioComun) {

        return HorarioComunBasicDTO.builder()
                .nombre(horarioComun.getNombre())
                .equipoAsociado(EquipoMapper.toEquipoBasicDTO(horarioComun.getEquipoAsociado()))
                .build();
    }

    public static HorarioComunDetailDTO toHorarioComunDetailDTO(HorariosComun horarioComun) {

        return HorarioComunDetailDTO.builder()
                .idHorarioComun(horarioComun.getIdHorarioComun())
                .nombre(horarioComun.getNombre())
                .equipoAsociado(EquipoMapper.toEquipoBasicDTO(horarioComun.getEquipoAsociado()))
                .horarios(HorarioMapper.ListtoHorarioDetailDTO(horarioComun.getHorarios()))
                .build();
    }

    public static List<HorarioComunDetailDTO> ListtoHorarioComunDetailDTO(List<HorariosComun> horariosComunes) {
        if (horariosComunes == null) {
            return new ArrayList<>();
        }
        List<HorarioComunDetailDTO> listaHorariosComunes = new ArrayList<>();
        for (HorariosComun horarioComun : horariosComunes) {
            if (horarioComun != null) {
                listaHorariosComunes.add(toHorarioComunDetailDTO(horarioComun));
            }
        }
        return listaHorariosComunes;
    }
}