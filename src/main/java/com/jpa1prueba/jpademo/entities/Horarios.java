package com.jpa1prueba.jpademo.entities;

import java.time.LocalDate;
import java.time.LocalTime;
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

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    protected Usuarios usuarioAsociado;

    @Column(name = "fecha")
    protected LocalDate fecha;

    @Column(name = "hora_inicio")
    protected LocalTime horaInicio;

    @Column(name = "hora_fin")
    protected LocalTime horaFin;
}
