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


    public Usuarios createUsuario(Usuarios usuario) {
        return usuarioRepository.save(usuario);
    }

 
    public void deleteUsuario(Long idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new RuntimeException("Usuario no encontrado para eliminar");
        }
        usuarioRepository.deleteById(idUsuario);
    }

    public Usuarios updateUsuario(Long idUsuario, Usuarios usuario) {
        // Verifica si el usuario existe
        Usuarios existingUsuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    
        // Actualiza los campos del usuario con los nuevos valores
        existingUsuario.setNombre(usuario.getNombre());
        existingUsuario.setEmail(usuario.getEmail());
        existingUsuario.setContrasena(usuario.getContrasena());
        existingUsuario.setTipo(usuario.getTipo());
        existingUsuario.setEquipoUser(usuario.getEquipoUser());
    
        // Guarda el usuario actualizado en la base de datos
        return usuarioRepository.save(existingUsuario);
    }
        
}
