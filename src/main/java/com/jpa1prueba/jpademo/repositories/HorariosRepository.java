package com.jpa1prueba.jpademo.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.jpa1prueba.jpademo.entities.Horarios;
import com.jpa1prueba.jpademo.entities.Usuarios;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface HorariosRepository extends JpaRepository<Horarios, Long> {
    List<Horarios> findByUsuarioAsociado(Usuarios Usuario);

    Optional<Horarios> findByNombre(String nombre);
}
