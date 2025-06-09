package com.jpa1prueba.scheduleorganizermodule.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizerRequestDTO {
    @JsonProperty("equipo")
    private EquipoDTO equipo;
    
    @JsonProperty("scheduleTrabajadores")
    private List<TrabajadorDTO> scheduleTrabajadores;
}

