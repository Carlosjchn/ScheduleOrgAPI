package com.jpa1prueba.scheduleorganizermodule.services;

import com.jpa1prueba.scheduleorganizermodule.dtos.OrganizerRequestDTO;
import com.jpa1prueba.scheduleorganizermodule.dtos.ScheduleResponseDTO;
import com.jpa1prueba.scheduleorganizermodule.dtos.SaveScheduleRequestDTO;
import com.jpa1prueba.jpademo.entities.HorariosComun;

public interface OrganizerService {
    ScheduleResponseDTO generateScheduleWithAI(OrganizerRequestDTO request);
    ScheduleResponseDTO generateScheduleWithORTools(OrganizerRequestDTO request);
    HorariosComun saveSchedule(SaveScheduleRequestDTO saveRequest, OrganizerRequestDTO originalRequest);
}
