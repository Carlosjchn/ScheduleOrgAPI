package com.jpa1prueba.jpademo.services;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa1prueba.jpademo.entities.Usuarios;
import com.jpa1prueba.jpademo.repositories.UserRepository;

@Service
@Transactional
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registerUser(Usuarios user) {
        // Check if the email already exists
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Encrypt the password and save the user
        user.setContrasena(passwordEncoder.encode(user.getContrasena()));
        userRepository.save(user);
        return "User registered successfully";
    }

    public Optional<Usuarios> loginUser(String NameOremail, String contrasena) {
        Optional<Usuarios> optionalUsuario = userRepository.findByNombreOrEmail(NameOremail);

        if (optionalUsuario.isPresent()) {
            Usuarios usuario = optionalUsuario.get();
            if (passwordEncoder.matches(contrasena, usuario.getContrasena())) {
                return optionalUsuario;
            }
            throw new RuntimeException("Invalid password");
        } else {
            throw new RuntimeException("User not found");
        }
    }
}