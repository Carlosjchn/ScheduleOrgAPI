package com.jpa1prueba.jpademo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpa1prueba.jpademo.dto.user.UserDetailDTO;
import com.jpa1prueba.jpademo.entities.Usuarios;

import com.jpa1prueba.jpademo.services.UsuarioService;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController{

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/all")
    public List<UserDetailDTO> getAllUsers() {
        return usuarioService.getAll();
    }

    @PostMapping("/login")
public ResponseEntity<Object> login(@RequestParam String nameOrMail, @RequestParam String password) {
    try {
        // Attempt login with provided credentials
        UserDetailDTO user = usuarioService.login(nameOrMail, password);
        return ResponseEntity.ok(user);  // Return the user details if login is successful
    } catch (IllegalArgumentException e) {
        // Bad request when inputs are invalid (e.g., empty fields)
        return ResponseEntity.badRequest().body("Datos incorrectos.");  
    } catch (RuntimeException e) {
        // Unauthorized when credentials are incorrect
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .body("Credenciales incorrectas"); // Simple error message
    }
}


    @GetMapping("/id/{id}")
    public UserDetailDTO getUserById(@PathVariable Long id) {
        return usuarioService.getUsuarioDTOById(id);
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
