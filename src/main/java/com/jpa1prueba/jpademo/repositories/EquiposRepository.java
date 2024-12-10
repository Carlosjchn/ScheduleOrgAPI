package com.jpa1prueba.jpademo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa1prueba.jpademo.entities.Equipos;

public interface EquiposRepository extends JpaRepository<Equipos, Long> {
}

