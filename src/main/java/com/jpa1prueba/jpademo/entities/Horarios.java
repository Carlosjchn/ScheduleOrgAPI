package com.jpa1prueba.jpademo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="horarios")
public class Horarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario")
    protected long idHorario;

    @Column(name = "id_usuario")
    protected long idUsuario;

    @Column(name = "fecha")
    protected String fecha;

    @Column(name = "hora_incio")
    protected String horaInicio;

    @Column(name = "hora_fin")
    protected String horaFin;

}
