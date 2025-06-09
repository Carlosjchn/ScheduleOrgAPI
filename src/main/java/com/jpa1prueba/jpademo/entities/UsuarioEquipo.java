package com.jpa1prueba.jpademo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.jpa1prueba.jpademo.entities.enums.RolEquipo;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario_equipo")
public class UsuarioEquipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario_equipo")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuarios usuario;
    
    @ManyToOne
    @JoinColumn(name = "id_equipo", nullable = false)
    private Equipos equipo;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private RolEquipo rol = RolEquipo.MIEMBRO;
}