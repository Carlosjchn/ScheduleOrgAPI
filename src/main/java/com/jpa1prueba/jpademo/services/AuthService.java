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
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String loginUser(String NameOremail, String contrasena) {
        Optional<Usuarios> optionalUsuario = userRepository.findByNombreOrEmail(NameOremail);

        if (optionalUsuario.isPresent()) {
            Usuarios usuario = optionalUsuario.get();
            if (passwordEncoder.matches(contrasena, usuario.getContrasena())) {
                // Generate JWT token with user data
                return jwtService.generateToken(
                    usuario.getIdUsuario(),
                    usuario.getNombre(),
                    usuario.getTipo().toString()
                );
            }
            throw new RuntimeException("Invalid password");
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public boolean validateToken(String token) {
        return jwtService.isTokenValid(token);
    }

    public String getUserNameFromToken(String token) {
        return jwtService.extractUserName(token);
    }

    public String getUserIdFromToken(String token) {
        return jwtService.extractUserId(token);
    }

    public String getUserTypeFromToken(String token) {
        return jwtService.extractUserType(token);
    }

    public String getSecretKey() {
        return jwtService.getSecretKey();
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
}