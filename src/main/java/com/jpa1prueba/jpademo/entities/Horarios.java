package com.jpa1prueba.jpademo.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Horarios")
public class Horarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario")
    protected Long idHorario;

    @Column(name = "titulo")
    protected String nombre;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    protected Usuarios usuarioAsociado;

    /* 
    protected Equipos equipoAsociado;
    @ManyToOne
    @JoinColumn(name = "id_equipo", referencedColumnName = "id_equipo")
    */
    
    @OneToMany(mappedBy = "horario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<Eventos> eventos;

    @ManyToOne(optional = true)
    @JoinColumn(name = "id_horario_comun", referencedColumnName = "id_horario_comun", nullable = true)
    protected HorariosComun horarioComun;
}
