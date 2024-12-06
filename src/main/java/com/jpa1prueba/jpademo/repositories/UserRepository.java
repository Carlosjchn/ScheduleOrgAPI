package com.jpa1prueba.jpademo.repositories;

import org.springframework.stereotype.Repository;

import com.jpa1prueba.jpademo.entities.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 
    public User findByNombre(String nombre);

    public List<User> findByEmail(String email);

    public List<User> findByEquipoNombre(String nombre);

    public User findByNombreHorariosUser(String nombre);

    public User findByNombreOrEmail(String nombre, String email);

}
