package com.jpa1prueba.scheduleorganizermodule.mappers;

import com.jpa1prueba.scheduleorganizermodule.dtos.PreferenciasDTO;
import com.jpa1prueba.scheduleorganizermodule.dtos.HorarioSlotDTO;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class PreferenciasDTOMapper {
    
    public PreferenciasDTO createDefaultPreferences() {
        Map<String, HorarioSlotDTO> diasDefault = new HashMap<>();
        // No default preferences - empty map
        return PreferenciasDTO.builder()
                .dias(diasDefault)
                .build();
    }

    public PreferenciasDTO toDTO(Map<String, HorarioSlotDTO> dias) {
        return PreferenciasDTO.builder()
                .dias(dias)
                .build();
    }
} 