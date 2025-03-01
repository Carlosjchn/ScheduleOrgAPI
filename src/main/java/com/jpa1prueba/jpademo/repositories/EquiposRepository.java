package com.jpa1prueba.jpademo.repositories;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa1prueba.jpademo.entities.Equipos;

public interface EquiposRepository extends JpaRepository<Equipos, Long> {

    public Optional<Equipos> findByNombre(String nombre);

    Optional<Equipos> findByUsuariosIdUsuario(Long idUsuario);
}

