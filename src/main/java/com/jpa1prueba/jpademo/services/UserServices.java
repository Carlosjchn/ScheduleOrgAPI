package com.jpa1prueba.jpademo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa1prueba.jpademo.entities.User;
import com.jpa1prueba.jpademo.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserServices {
 
    @Autowired
    protected UserRepository userRepository;

    @Transactional
    public void createUsuario(User user) {
        userRepository.save(user);
    }
    
    public User getUsuarioByNombre(String nombre) {
        return userRepository.findByNombre(nombre);
    }

    public List<User> getUsuariosByEquipoNombre(String nombre) {
        return userRepository.findByEquipoNombre(nombre);
    }

}
