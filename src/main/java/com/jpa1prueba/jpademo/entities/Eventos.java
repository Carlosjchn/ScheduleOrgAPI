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
@Table(name = "Eventos")
public class Eventos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    protected Long idEvento;

    @Column(name = "titulo")
    protected String titulo;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    protected Usuarios usuarioAsociado;

    @Column(name = "fecha")
    protected LocalDate fecha;

    @Column(name = "hora_inicio")
    protected LocalTime horaInicio;

    @Column(name = "hora_fin")
    protected LocalTime horaFin;

    @ManyToOne
    @JoinColumn(name = "id_horario", referencedColumnName = "id_horario")
    protected Horarios horario;
}