package com.jpa1prueba.scheduleorganizermodule.mappers;

import com.jpa1prueba.jpademo.entities.HorariosComun;
import com.jpa1prueba.scheduleorganizermodule.dtos.HorarioGeneralDTO;
import org.springframework.stereotype.Component;

@Component
public class HorarioGeneralDTOMapper {
    
    public HorarioGeneralDTO toDTO(HorariosComun horarioComun) {
        return HorarioGeneralDTO.builder()
                .idHorario(horarioComun.getIdHorarioComun())
                .nombre(horarioComun.getNombre())
                .build();
    }

    public HorariosComun toEntity(HorarioGeneralDTO dto) {
        HorariosComun horarioComun = new HorariosComun();
        horarioComun.setIdHorarioComun(dto.getIdHorario());
        horarioComun.setNombre(dto.getNombre());
        return horarioComun;
    }
} 