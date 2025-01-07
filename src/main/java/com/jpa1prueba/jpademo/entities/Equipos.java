package com.jpa1prueba.jpademo.entities;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Equipos")
public class Equipos {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name ="id_equipo")
    protected Long idEquipo;

    @Column(name="tipo")
    protected String tipo;

    @Column(name="nombre")
    protected String nombre;
    
    @Column(name="horas_inicio_act")
    protected LocalTime horaInicioAct;

    @Column(name="hora_fin_act")
    protected LocalTime horaFinAct;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipoUser", fetch = FetchType.LAZY)
    protected List<Usuarios> usuarios;

    public Optional<Equipos> map(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'map'");
    }

}
