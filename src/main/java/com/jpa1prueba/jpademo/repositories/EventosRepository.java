package com.jpa1prueba.jpademo.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.jpa1prueba.jpademo.entities.Eventos;
import com.jpa1prueba.jpademo.entities.Usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface EventosRepository extends JpaRepository<Eventos, Long> {

    public List<Eventos> findByUsuarioAsociado(Usuarios Usuario);

    @Query("SELECT e FROM Eventos e WHERE e.usuarioAsociado = :usuario AND MONTH(e.fecha) = :mes")
    List<Eventos> findByUsuarioAsociadoAndMonth(@Param("usuario") Usuarios usuario, @Param("mes") int mes);

    @Query("SELECT e FROM Eventos e WHERE e.usuarioAsociado = :usuarioAsociado AND e.fecha = :fecha")
    Optional<Eventos> findByUsuarioAsociadoAndFecha(@Param("usuarioAsociado") Usuarios usuarioAsociado, @Param("fecha") LocalDate fecha);
}