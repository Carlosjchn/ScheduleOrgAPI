package com.jpa1prueba.jpademo.repositories;

import org.springframework.stereotype.Repository;

import com.jpa1prueba.jpademo.entities.Usuarios;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserRepository extends JpaRepository<Usuarios, Long> {
 
    public Optional<Usuarios> findByNombre(String nombre);

    public Optional<Usuarios> findByEmail(String email);

    @Query("SELECT u FROM Usuarios u WHERE u.nombre = :nameOrMail OR u.email = :nameOrMail")
    public Optional<Usuarios> findByNombreOrEmail(@Param("nameOrMail") String nameOrMail);
    
    
    
    public Optional<Usuarios> findById(Long id);
}
