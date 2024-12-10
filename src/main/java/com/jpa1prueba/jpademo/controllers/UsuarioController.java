package com.jpa1prueba.jpademo.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.jpa1prueba.jpademo.dto.user.UserDetailDTO;
import com.jpa1prueba.jpademo.entities.Usuarios;

import com.jpa1prueba.jpademo.services.UsuarioService;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/all")
    public List<UserDetailDTO> getAllUsers() {
        return usuarioService.getAll();
    }

    @GetMapping("/id/{id}")
    public Usuarios getUserById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id);
    }

    @PostMapping("/create")
    public Usuarios createUsuario(@RequestBody Usuarios usuario) {
        return usuarioService.createUsuario(usuario);
    }

    @DeleteMapping("/delete/{idUsuario}")
    public void deleteUsuario(@PathVariable Long idUsuario) {
        usuarioService.deleteUsuario(idUsuario);
    }
}

