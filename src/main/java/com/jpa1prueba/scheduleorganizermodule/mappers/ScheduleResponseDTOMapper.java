package com.jpa1prueba.scheduleorganizermodule.mappers;

import com.jpa1prueba.scheduleorganizermodule.dtos.ScheduleSlotDTO;
import com.jpa1prueba.scheduleorganizermodule.dtos.ScheduleResponseDTO;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.List;

@Component
public class ScheduleResponseDTOMapper {
    
    public ScheduleResponseDTO toDTO(Map<String, List<ScheduleSlotDTO>> commonSchedule) {
        return ScheduleResponseDTO.builder()
                .commonSchedule(commonSchedule)
                .build();
    }

    public Map<String, List<ScheduleSlotDTO>> toEntity(ScheduleResponseDTO dto) {
        return dto.getCommonSchedule();
    }
} 