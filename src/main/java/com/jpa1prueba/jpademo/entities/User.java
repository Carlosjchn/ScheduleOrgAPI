package com.jpa1prueba.jpademo.entities;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

enum TipoUser {
    TRABAJADOR,
    JEFE,
    ADMIN
}

@Data

@Entity
@Table(name = "usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    protected long idUsuario; 

    @Column(name="tipo")
    protected TipoUser tipo;

    @Column(name="nombre")
    protected String nombre;

    @Column(name = "email")
    protected String email;

    @Column(name = "contrasena")
    protected String contrasena;

    @ManyToAny()
    @JoinColumn(
        name = "id_horario",
        referencedColumnName = "id_horario"
    )
    protected Equipo equipoAsociado;

}
