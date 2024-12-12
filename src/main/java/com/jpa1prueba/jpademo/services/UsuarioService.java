package com.jpa1prueba.jpademo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa1prueba.jpademo.dto.user.UserDetailDTO;
import com.jpa1prueba.jpademo.entities.Usuarios;
import com.jpa1prueba.jpademo.mappers.UserMapper;
import com.jpa1prueba.jpademo.repositories.UserRepository;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UserRepository usuarioRepository;

    /**
     * Retrieves a user by ID.
     */
    public Usuarios getUsuarioById(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public UserDetailDTO getUsuarioDTOById(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .map(UserMapper::toUserDetailDTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public UserDetailDTO getUsuarioByNombre(String idUsuario) {
        return usuarioRepository.findByNombre(idUsuario)
                .map(UserMapper::toUserDetailDTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    
    
    public UserDetailDTO getUserByNombreOrEmail(String nameOrMail) {
        return usuarioRepository.findByNombreOrEmail(nameOrMail)
        .map(UserMapper::toUserDetailDTO)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }


    public UserDetailDTO login(String nameOrMail, String password) {


        UserDetailDTO user = getUserByNombreOrEmail(nameOrMail);
        if (user != null && password.equals(user.getContrasena())) {
            return user;
        }
        
        throw new RuntimeException("Credenciales incorrectas");
    }

    public List<UserDetailDTO> getAll() {
        return usuarioRepository.findAll()
                .stream()
                .map(UserMapper::toUserDetailDTO)
                .toList();
    }

    /**
     * Creates a new user.
     */
    public Usuarios createUsuario(Usuarios usuario) {
        return usuarioRepository.save(usuario);
    }

    /**
     * Deletes a user by ID.
     */
    public void deleteUsuario(Long idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new RuntimeException("Usuario no encontrado para eliminar");
        }
        usuarioRepository.deleteById(idUsuario);
    }

    
        
}
