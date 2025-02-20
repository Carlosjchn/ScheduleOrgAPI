package com.jpa1prueba.jpademo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa1prueba.jpademo.dto.user.UserDetailDTO;
import com.jpa1prueba.jpademo.entities.Usuarios;
import com.jpa1prueba.jpademo.mappers.UserMapper;
import com.jpa1prueba.jpademo.repositories.UserRepository;

@Service
@Transactional
public class UsuarioService {

    private final UserRepository userRepository;

    public UsuarioService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Usuarios getUsuarioById(Long idUsuario) {
        return userRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public UserDetailDTO getUsuarioDTOById(Long idUsuario) {
        return userRepository.findById(idUsuario)
                .map(UserMapper::toUserDetailDTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public UserDetailDTO getUsuarioByNombre(String idUsuario) {
        return userRepository.findByNombre(idUsuario)
                .map(UserMapper::toUserDetailDTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public UserDetailDTO getUserByNombreOrEmail(String nameOrMail) {
        return userRepository.findByNombreOrEmail(nameOrMail)
                .map(UserMapper::toUserDetailDTO)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public List<UserDetailDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toUserDetailDTO)
                .toList();
    }

    public Usuarios createUsuario(Usuarios usuario) {
        return userRepository.save(usuario);
    }

    public void deleteUsuario(Long idUsuario) {
        if (!userRepository.existsById(idUsuario)) {
            throw new RuntimeException("Usuario no encontrado para eliminar");
        }
        userRepository.deleteById(idUsuario);
    }

    public Usuarios updateUsuario(Long idUsuario, Usuarios usuario) {
        // Verifica si el usuario existe
        Usuarios existingUsuario = userRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Actualiza los campos del usuario con los nuevos valores
        existingUsuario.setNombre(usuario.getNombre());
        existingUsuario.setEmail(usuario.getEmail());
        existingUsuario.setContrasena(usuario.getContrasena());
        existingUsuario.setTipo(usuario.getTipo());
        existingUsuario.setEquipoUser(usuario.getEquipoUser());

        // Guarda el usuario actualizado en la base de datos
        return userRepository.save(existingUsuario);
    }

}
