package com.jpa1prueba.jpademo.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.ArrayList;
import com.jpa1prueba.jpademo.entities.enums.TipoUser;
import com.jpa1prueba.jpademo.entities.enums.RolEquipo;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Usuarios")
public class Usuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    protected Long idUsuario; 
    
    @Enumerated(EnumType.STRING)
    @Column(name="tipo", nullable = false)
    protected TipoUser tipo = TipoUser.USUARIO;  // Valor por defecto actualizado
    
    @Column(name="nombre")
    protected String nombre;

    @Column(name = "email")
    protected String email;

    @Column(name = "contrasena")
    protected String contrasena;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<UsuarioEquipo> equipos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioAsociado", fetch = FetchType.LAZY)
    protected List<Eventos> eventos;
    
    // Método de conveniencia para agregar un equipo con un rol específico
    public void agregarEquipo(Equipos equipo, RolEquipo rol) {
        UsuarioEquipo usuarioEquipo = new UsuarioEquipo();
        usuarioEquipo.setUsuario(this);
        usuarioEquipo.setEquipo(equipo);
        usuarioEquipo.setRol(rol);
        this.equipos.add(usuarioEquipo);
        equipo.getUsuariosEquipo().add(usuarioEquipo);
    }
}
