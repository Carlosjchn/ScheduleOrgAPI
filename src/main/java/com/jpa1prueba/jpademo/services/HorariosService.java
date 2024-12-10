package com.jpa1prueba.jpademo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa1prueba.jpademo.entities.Horarios;
import com.jpa1prueba.jpademo.entities.Usuarios;
import com.jpa1prueba.jpademo.repositories.HorariosRepository;

import java.util.List;

@Service
public class HorariosService {

    @Autowired
    private HorariosRepository horariosRepository;

    public List<Horarios> getHorariosByUsuario(Usuarios usuario) {
        return horariosRepository.findByUsuarioAsociado(usuario);
    }

    public List<Horarios> getAllHorarios() {
        return horariosRepository.findAll();
    }

    public Horarios createHorario(Horarios horario) {
        return horariosRepository.save(horario);
    }

    public void deleteHorario(Long idHorario) {
        horariosRepository.deleteById(idHorario);
    }
}

