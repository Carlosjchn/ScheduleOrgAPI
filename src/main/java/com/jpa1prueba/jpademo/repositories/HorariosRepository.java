package com.jpa1prueba.jpademo.repositories;

import java.util.List;


import org.springframework.stereotype.Repository;


import com.jpa1prueba.jpademo.entities.Horarios;
import com.jpa1prueba.jpademo.entities.Usuarios;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface HorariosRepository extends JpaRepository<Horarios, Long> {
    


    public List<Horarios> findByUsuarioAsociado(Usuarios Usuario);




}
