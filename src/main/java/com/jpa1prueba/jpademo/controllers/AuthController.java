package com.jpa1prueba.jpademo.controllers;

import com.jpa1prueba.jpademo.dto.auth.LoginRequest;
import com.jpa1prueba.jpademo.dto.auth.RegisterRequest;
import com.jpa1prueba.jpademo.entities.Usuarios;
import com.jpa1prueba.jpademo.services.UsuarioService;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final UsuarioService usuariosService;

    public AuthController(UsuarioService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Usuarios user) {
        try {
            String message = usuariosService.registerUser(user);
            return ResponseEntity.ok(message);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest request) {
        Optional<Usuarios> optionalUsuario = usuariosService.loginUser(request.getNombreoremail(), request.getContrasena());

        if (optionalUsuario.isPresent()) {
            Usuarios usuario = optionalUsuario.get();
            return "Welcome " + usuario.getNombre();
        } else {
            return "Invalid credentials";
        }
    }
}
