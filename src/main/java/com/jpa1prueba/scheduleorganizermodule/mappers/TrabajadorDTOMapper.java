package com.jpa1prueba.scheduleorganizermodule.mappers;

import com.jpa1prueba.jpademo.entities.Usuarios;
import com.jpa1prueba.scheduleorganizermodule.dtos.TrabajadorDTO;
import com.jpa1prueba.scheduleorganizermodule.dtos.PreferenciasDTO;
import com.jpa1prueba.scheduleorganizermodule.dtos.HorarioGeneralDTO;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TrabajadorDTOMapper {
    
    private final PreferenciasDTOMapper preferenciasDTOMapper;
    private final HorarioGeneralDTOMapper horarioGeneralDTOMapper;
    
    public TrabajadorDTO toDTO(Usuarios usuario) {
        return TrabajadorDTO.builder()
                .id(usuario.getIdUsuario().toString())
                .nombre(usuario.getNombre())
                .preferencias(preferenciasDTOMapper.createDefaultPreferences())
                .restricciones(preferenciasDTOMapper.createDefaultPreferences())
                .horarioGeneral(horarioGeneralDTOMapper.toDTO(null))
                .build();
    }

    public Usuarios toEntity(TrabajadorDTO dto) {
        Usuarios usuario = new Usuarios();
        usuario.setIdUsuario(Long.parseLong(dto.getId()));
        usuario.setNombre(dto.getNombre());
        // Note: Other fields are not mapped to entity as they are used only for schedule generation
        return usuario;
    }
} 