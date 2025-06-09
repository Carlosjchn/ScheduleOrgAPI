package com.jpa1prueba.jpademo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jpa1prueba.jpademo.dto.user.UserDetailDTO;
import com.jpa1prueba.jpademo.entities.Equipos;
import com.jpa1prueba.jpademo.entities.UsuarioEquipo;
import com.jpa1prueba.jpademo.entities.Usuarios;
import com.jpa1prueba.jpademo.entities.enums.RolEquipo;
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
        // Ya no existe equipoUser, ahora se maneja a través de la relación equipos
        // existingUsuario.setEquipoUser(usuario.getEquipoUser());

        // Guarda el usuario actualizado en la base de datos
        return userRepository.save(existingUsuario);
    }

    @Autowired
    private EquiposService equiposService;

    public void unirseAEquipo(Long idUsuario, Long idEquipo) {
        Usuarios usuario = getUsuarioById(idUsuario);
        Equipos equipo = equiposService.getEquipoById(idEquipo);
        
        if (equipo == null) {
            throw new RuntimeException("Equipo no encontrado");
        }

        // Verificar si el usuario ya está en el equipo
        boolean yaEstaEnEquipo = usuario.getEquipos().stream()
                .anyMatch(ue -> ue.getEquipo().getIdEquipo().equals(idEquipo));

        if (yaEstaEnEquipo) {
            throw new RuntimeException("El usuario ya es miembro de este equipo");
        }

        // Crear nueva relación usuario-equipo
        UsuarioEquipo usuarioEquipo = new UsuarioEquipo();
        usuarioEquipo.setUsuario(usuario);
        usuarioEquipo.setEquipo(equipo);
        usuarioEquipo.setRol(RolEquipo.MIEMBRO);

        // Agregar la relación a ambos lados
        usuario.getEquipos().add(usuarioEquipo);
        equipo.getUsuariosEquipo().add(usuarioEquipo);

        // Guardar el usuario actualizado (cascadeará los cambios)
        userRepository.save(usuario);
    }
}
