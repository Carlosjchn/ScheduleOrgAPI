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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import com.jpa1prueba.jpademo.entities.enums.TipoUser;

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
    protected TipoUser tipo = TipoUser.INDEFINIDO;  // Set default value
    @Column(name="nombre")
    protected String nombre;

    @Column(name = "email")
    protected String email;

    @Column(name = "contrasena")
    protected String contrasena;

    @ManyToOne()
    @JoinColumn(
        name = "id_equipo",
        referencedColumnName = "id_equipo",
        nullable = true
    )
    protected Equipos equipoUser;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioAsociado", fetch = FetchType.LAZY)
    protected List<Horarios> horarios;

}
