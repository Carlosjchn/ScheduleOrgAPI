package com.jpa1prueba.jpademo.controllers;

import com.jpa1prueba.jpademo.dto.auth.AuthResponse;
import com.jpa1prueba.jpademo.dto.auth.LoginRequest;
import com.jpa1prueba.jpademo.entities.Usuarios;
import com.jpa1prueba.jpademo.entities.enums.TipoUser;
import com.jpa1prueba.jpademo.services.AuthService;
import com.jpa1prueba.jpademo.dto.auth.RegisterRequest;
import com.nimbusds.jwt.JWTClaimsSet;

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
            String jweToken = authService.loginUser(request.getNombreoremail(), request.getContrasena());
            
            if (jweToken != null) {
                // Just send the token, user data is encrypted inside
                AuthResponse response = AuthResponse.builder()
                    .token(jweToken)
                    .build();
                
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(401).body("Invalid credentials");
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping("/encryption-key")
    public ResponseEntity<String> getSecretKey() {
        // In production, this should be properly secured
        return ResponseEntity.ok(authService.getSecretKey());
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
