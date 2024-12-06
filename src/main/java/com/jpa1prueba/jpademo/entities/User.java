package com.jpa1prueba.jpademo.entities;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

enum TipoUser {
    TRABAJADOR,
    JEFE,
    ADMIN
}

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @ManyToOne()
    @JoinColumn(
        name = "id_equipo",
        referencedColumnName = "id_equipo"
    )
    protected Equipo equipoUser;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioAsociado", fetch = FetchType.LAZY)
    protected List<Horarios> horariosUser;

}
