package com.jpa1prueba.jpademo.entities;

import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.jpa1prueba.jpademo.entities.enums.RolEquipo;

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
    
    @Column(name="hora_inicio_act")
    protected LocalTime horaInicioAct;

    @Column(name="hora_fin_act")
    protected LocalTime horaFinAct;

    @OneToMany(mappedBy = "equipo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    protected List<UsuarioEquipo> usuariosEquipo = new ArrayList<>();
    
    // Método para obtener el jefe del equipo
    public Optional<Usuarios> getJefe() {
        return usuariosEquipo.stream()
                .filter(ue -> ue.getRol() == RolEquipo.JEFE)
                .map(UsuarioEquipo::getUsuario)
                .findFirst();
    }
    
    // Método para obtener los subjefes del equipo
    public List<Usuarios> getSubjefes() {
        return usuariosEquipo.stream()
                .filter(ue -> ue.getRol() == RolEquipo.SUBJEFE)
                .map(UsuarioEquipo::getUsuario)
                .collect(Collectors.toList());
    }
    
    // Método para establecer un jefe
    public void setJefe(Usuarios usuario) {
        // Primero verificamos si ya existe un jefe
        Optional<UsuarioEquipo> jefeActual = usuariosEquipo.stream()
                .filter(ue -> ue.getRol() == RolEquipo.JEFE)
                .findFirst();
        
        // Si existe, cambiamos su rol a miembro
        jefeActual.ifPresent(ue -> ue.setRol(RolEquipo.MIEMBRO));
        
        // Buscamos si el usuario ya está en el equipo
        Optional<UsuarioEquipo> usuarioEnEquipo = usuariosEquipo.stream()
                .filter(ue -> ue.getUsuario().equals(usuario))
                .findFirst();
        
        if (usuarioEnEquipo.isPresent()) {
            // Si ya está en el equipo, actualizamos su rol a jefe
            usuarioEnEquipo.get().setRol(RolEquipo.JEFE);
        } else {
            // Si no está en el equipo, lo agregamos como jefe
            UsuarioEquipo nuevoJefe = new UsuarioEquipo();
            nuevoJefe.setUsuario(usuario);
            nuevoJefe.setEquipo(this);
            nuevoJefe.setRol(RolEquipo.JEFE);
            usuariosEquipo.add(nuevoJefe);
            usuario.getEquipos().add(nuevoJefe);
        }
    }
}
