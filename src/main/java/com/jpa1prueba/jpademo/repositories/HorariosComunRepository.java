package com.jpa1prueba.jpademo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jpa1prueba.jpademo.entities.HorariosComun;
import com.jpa1prueba.jpademo.entities.Equipos;

@Repository
public interface HorariosComunRepository extends JpaRepository<HorariosComun, Long> {
    List<HorariosComun> findByEquipoAsociado(Equipos equipo);
}