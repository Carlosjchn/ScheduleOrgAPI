package com.jpa1prueba.jpademo.entities;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;



@Data

@Entity
@Table(name = "equipos")
public class Equipo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name ="id_equipo")
    protected long idEquipo;

    @Column(name="tipo")
    protected String tipo;

    @Column(name="nombre")
    protected String nombre;
    
    @Column(name="horas_inicio_act")
    protected Date horaInicioAct;

    @Column(name="hora_fin_act")
    protected Date horaFinAct;

    @OneToMany(mappedBy = "equipoAsociado")
    protected List<User> usuarios;

}
