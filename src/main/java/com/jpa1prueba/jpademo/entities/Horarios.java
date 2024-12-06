package com.jpa1prueba.jpademo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "horarios")
public class Horarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario")
    protected long idHorario;

    @ManyToOne
    @JoinColumn
    (name="id_usuario",
    referencedColumnName = "id_usuario")
    protected long usuarioAsociado;

    @Column(name = "fecha")
    protected String fecha;

    @Column(name = "hora_incio")
    protected String horaInicio;

    @Column(name = "hora_fin")
    protected String horaFin;

}
