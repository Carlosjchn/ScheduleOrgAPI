package com.jpa1prueba.jpademo.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jpa1prueba.jpademo.entities.Horarios;
import com.jpa1prueba.jpademo.entities.Usuarios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface HorariosRepository extends JpaRepository<Horarios, Long> {

    public List<Horarios> findByUsuarioAsociado(Usuarios Usuario);

    @Query("SELECT h FROM Horarios h WHERE h.usuarioAsociado = :usuario AND MONTH(h.fecha) = :mes")
    List<Horarios> findByUsuarioAsociadoAndMonth(@Param("usuario") Usuarios usuario, @Param("mes") int mes);

}
