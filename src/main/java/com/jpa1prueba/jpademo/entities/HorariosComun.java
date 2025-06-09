package com.jpa1prueba.jpademo.entities;

import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "HorariosComun")
public class HorariosComun {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario_comun")
    protected Long idHorarioComun;

    @Column(name = "titulo")
    protected String nombre;

    @ManyToOne
    @JoinColumn(name = "id_equipo", referencedColumnName = "id_equipo")
    protected Equipos equipoAsociado;

    @OneToMany(mappedBy = "horarioComun", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<Horarios> horarios;
}
