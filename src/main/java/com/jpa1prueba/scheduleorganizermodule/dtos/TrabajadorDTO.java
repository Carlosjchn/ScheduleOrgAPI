package com.jpa1prueba.scheduleorganizermodule.dtos;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrabajadorDTO {
    private String id;
    private String nombre;
    private PreferenciasDTO preferencias;
    private PreferenciasDTO restricciones;
    private HorarioGeneralDTO horarioGeneral;
} 