package com.jpa1prueba.jpademo.controllers;

import com.jpa1prueba.jpademo.dto.auth.AuthResponse;
import com.jpa1prueba.jpademo.dto.auth.LoginRequest;
import com.jpa1prueba.jpademo.entities.Usuarios;
import com.jpa1prueba.jpademo.entities.enums.TipoUser;
import com.jpa1prueba.jpademo.services.AuthService;
import com.jpa1prueba.jpademo.dto.auth.RegisterRequest;


import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;
   

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login") 
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest request) {
        try {
            Optional<Usuarios> optionalUsuario = authService.loginUser(request.getNombreoremail(), request.getContrasena());
            
            if (optionalUsuario.isPresent()) {
                Usuarios usuario = optionalUsuario.get();
                
                AuthResponse response = AuthResponse.builder()
                    .token(usuario.getIdUsuario())
                    .userName(usuario.getNombre())
                    .userType(usuario.getTipo().toString())
                    .build();
                
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(401).body("Invalid credentials");
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
        
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest request) {
        try {
            Usuarios nuevoUsuario = new Usuarios();
            nuevoUsuario.setNombre(request.getNombre());
            nuevoUsuario.setEmail(request.getEmail());
            nuevoUsuario.setContrasena(request.getContrasena());
            // tipo will use the default value from the entity
            
            authService.registerUser(nuevoUsuario);
            return ResponseEntity.ok("Usuario registrado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Fallo en el registro del usuario: " + e.getMessage());
        }
    }
}
