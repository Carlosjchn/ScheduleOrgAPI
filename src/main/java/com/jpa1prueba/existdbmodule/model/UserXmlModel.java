package com.jpa1prueba.existdbmodule.model;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.jpa1prueba.jpademo.dto.user.UserDetailDTO;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonRootName("user")
public class UserXmlModel {

    @XmlElement(name = "id")
    private Long id;
    
    @XmlElement(name = "nombre")
    private String nombre;
    
    @XmlElement(name = "email")
    private String email;
    
    @XmlElement(name = "equipo")
    private EquipoInfo equipo;
    
    @XmlElement(name = "horarios")
    private List<HorarioInfo> horarios;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class EquipoInfo {
        
        @XmlElement(name = "nombre")
        private String nombre;
        
        @XmlElement(name = "tipo")
        private String tipo;

    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class HorarioInfo {
        @XmlElement(name = "id")
        private Long id;
        
        @XmlElement(name = "fecha")
        private String fecha;
        
        @XmlElement(name = "horaInicio")
        private String horaInicio;
        
        @XmlElement(name = "horaFin")
        private String horaFin;
    }
    
    // Converter method from UserDetailDTO
    public static UserXmlModel fromUserDetailDTO(UserDetailDTO dto) {
        UserXmlModel model = new UserXmlModel();
        model.setId(dto.getIdUsuario());  // Changed from getId() to getIdUsuario()
        model.setNombre(dto.getNombre());
        model.setEmail(dto.getEmail());
        
        
        if (dto.getEquipoAsociado() != null) {
            var equipoDto = dto.getEquipoAsociado();
            EquipoInfo equipo = new EquipoInfo(
                equipoDto.getNombre(),
                equipoDto.getTipo()     // Changed from getHoraFin() to getHoraFinAct()
            );
            model.setEquipo(equipo);
        }
        
        if (dto.getHorariosUser() != null) {
            List<HorarioInfo> horarios = dto.getHorariosUser().stream()
                .map(h -> new HorarioInfo(
                    h.getIdHorario(),
                    h.getFecha().toString(),  // Convert LocalDate to String
                    h.getHoraInicio().toString(),  // Convert LocalTime to String
                    h.getHoraFin().toString()  // Convert LocalTime to String
                ))
                .toList();
            model.setHorarios(horarios);
        }
        
        return model;
    }
}