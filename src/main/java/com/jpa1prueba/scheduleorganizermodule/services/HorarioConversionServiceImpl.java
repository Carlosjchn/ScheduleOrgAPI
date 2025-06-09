package com.jpa1prueba.scheduleorganizermodule.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.DayOfWeek;
import java.time.temporal.TemporalAdjusters;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.jpa1prueba.jpademo.entities.*;
import com.jpa1prueba.jpademo.repositories.*;
import com.jpa1prueba.scheduleorganizermodule.dtos.ScheduleSlotDTO;
import com.jpa1prueba.scheduleorganizermodule.dtos.OrganizerRequestDTO;
import com.jpa1prueba.scheduleorganizermodule.dtos.ScheduleResponseDTO;

@Service
@RequiredArgsConstructor
public class HorarioConversionServiceImpl implements HorarioConversionService {

    private final HorariosComunRepository horariosComunRepository;
    private final HorariosRepository horariosRepository;
    private final EventosRepository eventosRepository;
    private final EquiposRepository equiposRepository;
    private final UserRepository userRepository;

    private static final Map<String, DayOfWeek> DIAS_SEMANA = new HashMap<>();
    static {
        DIAS_SEMANA.put("Lunes", DayOfWeek.MONDAY);
        DIAS_SEMANA.put("Martes", DayOfWeek.TUESDAY);
        DIAS_SEMANA.put("Miércoles", DayOfWeek.WEDNESDAY);
        DIAS_SEMANA.put("Jueves", DayOfWeek.THURSDAY);
        DIAS_SEMANA.put("Viernes", DayOfWeek.FRIDAY);
        DIAS_SEMANA.put("Sábado", DayOfWeek.SATURDAY);
        DIAS_SEMANA.put("Domingo", DayOfWeek.SUNDAY);
    }

    @Override
    @Transactional
    public HorariosComun convertAndSave(ScheduleResponseDTO scheduleResponse, OrganizerRequestDTO originalRequest) {
        // Buscar el equipo en la base de datos
        Equipos equipo = equiposRepository.findById(originalRequest.getEquipo().getIdEquipo())
            .orElseThrow(() -> new IllegalArgumentException("Equipo no encontrado"));
            
        // Crear el horario común
        HorariosComun horarioComun = new HorariosComun();
        horarioComun.setNombre("Horario generado para " + equipo.getNombre());
        horarioComun.setEquipoAsociado(equipo);
        
        // Lista para almacenar todos los horarios individuales
        Map<String, Horarios> horariosIndividuales = new HashMap<>();
        
        // Obtener la fecha del próximo lunes para empezar la semana
        LocalDate fechaInicioSemana = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        
        // Procesar el schedule para cada día
        for (Map.Entry<String, List<ScheduleSlotDTO>> entry : scheduleResponse.getCommonSchedule().entrySet()) {
            String nombreDia = entry.getKey();
            List<ScheduleSlotDTO> slotsDelDia = entry.getValue();
            
            // Calcular la fecha para este día
            DayOfWeek diaSemana = DIAS_SEMANA.get(nombreDia);
            LocalDate fechaEvento = fechaInicioSemana.with(TemporalAdjusters.nextOrSame(diaSemana));
            
            // Procesar cada slot
            for (ScheduleSlotDTO slot : slotsDelDia) {
                // Obtener o crear el horario individual para este trabajador
                Horarios horarioIndividual = horariosIndividuales.computeIfAbsent(slot.getWorkerId(), workerId -> {
                    Usuarios usuario = userRepository.findById(Long.parseLong(workerId))
                        .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + workerId));
                    
                    Horarios h = new Horarios();
                    h.setNombre("Horario de " + usuario.getNombre());
                    h.setUsuarioAsociado(usuario);
                    h.setHorarioComun(horarioComun);
                    h.setEventos(new ArrayList<>());
                    return h;
                });

                // Crear el evento
                Eventos evento = new Eventos();
                evento.setTitulo("Turno de trabajo");
                evento.setUsuarioAsociado(horarioIndividual.getUsuarioAsociado());
                evento.setFecha(fechaEvento);
                evento.setHoraInicio(LocalTime.parse(slot.getStartTime()));
                evento.setHoraFin(LocalTime.parse(slot.getEndTime()));
                evento.setHorario(horarioIndividual);
                
                horarioIndividual.getEventos().add(evento);
            }
        }
        
        // Asignar los horarios individuales al horario común
        horarioComun.setHorarios(new ArrayList<>(horariosIndividuales.values()));
        
        // Guardar todo en cascada
        return horariosComunRepository.save(horarioComun);
    }
} 