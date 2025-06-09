package com.jpa1prueba.scheduleorganizermodule.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveScheduleRequestDTO {
    @NotNull
    private Object scheduleData; // Using Object type to accept any JSON structure for now
} 