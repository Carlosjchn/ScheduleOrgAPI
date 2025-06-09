package com.jpa1prueba.scheduleorganizermodule.dtos;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleResponseDTO {
    private Map<String, List<ScheduleSlotDTO>> commonSchedule;
} 