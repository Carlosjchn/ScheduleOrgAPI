package com.jpa1prueba.scheduleorganizermodule.services;

import com.jpa1prueba.jpademo.entities.*;
import com.jpa1prueba.scheduleorganizermodule.dtos.ScheduleResponseDTO;
import com.jpa1prueba.scheduleorganizermodule.dtos.OrganizerRequestDTO;
 
public interface HorarioConversionService {
    HorariosComun convertAndSave(ScheduleResponseDTO scheduleResponse, OrganizerRequestDTO originalRequest);
} 