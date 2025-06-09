package com.jpa1prueba.jpademo.repositories;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jpa1prueba.jpademo.entities.Equipos;

public interface EquiposRepository extends JpaRepository<Equipos, Long> {

    public Optional<Equipos> findByNombre(String nombre);

    // Método actualizado para buscar equipos por id de usuario usando la relación UsuarioEquipo
    @Query("SELECT e FROM Equipos e JOIN e.usuariosEquipo ue WHERE ue.usuario.idUsuario = :idUsuario")
    Optional<Equipos> findByUsuarioId(@Param("idUsuario") Long idUsuario);
}

