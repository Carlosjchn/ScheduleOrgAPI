package com.jpa1prueba.scheduleorganizermodule.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipoDTO {
    @JsonProperty("idEquipo")
    private Long idEquipo;
    
    @JsonProperty("tipo")
    private String tipo;
    
    @JsonProperty("nombre")
    private String nombre;
    
    @JsonProperty("diasActividad")
    private String diasActividad;
    
    @JsonProperty("horaInicioActividad")
    private String horaInicioActividad;
    
    @JsonProperty("horaFinActividad")
    private String horaFinActividad;
    
    @JsonProperty("horasMinDiaria")
    private Integer horasMinDiaria;
    
    @JsonProperty("horasMaxDiaria")
    private Integer horasMaxDiaria;
} 