package com.jpa1prueba.jpademo.repositories;

import org.springframework.stereotype.Repository;

import com.jpa1prueba.jpademo.entities.Usuarios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<Usuarios, Long> {
 
    public Usuarios findByNombre(String nombre);

    public List<Usuarios> findByEmail(String email);

    public Usuarios findByNombreOrEmail(String nombre, String email);
    
    @EntityGraph(attributePaths = {"horarios"})
    Optional<Usuarios> findById(Long id);
}
