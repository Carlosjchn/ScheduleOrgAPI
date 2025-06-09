package com.jpa1prueba.scheduleorganizermodule.dtos;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HorarioSlotDTO {
    private String horaInicio;
    private String horaFin;
} 