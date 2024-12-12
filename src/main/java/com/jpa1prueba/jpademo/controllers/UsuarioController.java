package com.jpa1prueba.jpademo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpa1prueba.jpademo.dto.user.UserDetailDTO;
import com.jpa1prueba.jpademo.entities.Usuarios;
import com.jpa1prueba.jpademo.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

/**
 * Controlador para gestionar los usuarios en el sistema.
 */
@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Obtiene todos los usuarios registrados en el sistema.
     * 
     * @return Una lista de objetos `UserDetailDTO` que contienen los detalles de
     *         todos los usuarios.
     */
    @Operation(summary = "Obtener todos los usuarios", description = "Recupera todos los usuarios registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Usuarios recuperados con éxito.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDetailDTO.class)))
    @GetMapping("/all")
    public List<UserDetailDTO> getAllUsers() {
        return usuarioService.getAll();
    }

    /**
     * Realiza el inicio de sesión de un usuario utilizando el nombre de usuario o
     * correo y la contraseña.
     * 
     * @param nameOrMail Nombre de usuario o correo electrónico del usuario.
     * @param password   Contraseña del usuario.
     * @return Los detalles del usuario si el inicio de sesión es exitoso.
     */
    @Operation(summary = "Iniciar sesión", description = "Permite iniciar sesión en el sistema usando nombre de usuario/correo y contraseña.")
    @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDetailDTO.class)))
    @ApiResponse(responseCode = "400", description = "Datos incorrectos.", content = @Content(mediaType = "application/json"))
    @ApiResponse(responseCode = "401", description = "Credenciales incorrectas.", content = @Content(mediaType = "application/json"))
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestParam String nameOrMail, @RequestParam String password) {
        try {
            // Intentar iniciar sesión con las credenciales proporcionadas
            UserDetailDTO user = usuarioService.login(nameOrMail, password);
            return ResponseEntity.ok(user); // Devuelve los detalles del usuario si el inicio de sesión es exitoso
        } catch (IllegalArgumentException e) {
            // Solicitud incorrecta cuando los campos son inválidos
            return ResponseEntity.badRequest().body("Datos incorrectos.");
        } catch (RuntimeException e) {
            // No autorizado cuando las credenciales son incorrectas
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Credenciales incorrectas");
        }
    }

    /**
     * Obtiene los detalles de un usuario específico por su ID.
     * 
     * @param id ID del usuario a recuperar.
     * @return Un objeto `UserDetailDTO` con los detalles del usuario.
     */
    @Operation(summary = "Obtener usuario por ID", description = "Recupera los detalles de un usuario específico mediante su ID.")
    @ApiResponse(responseCode = "200", description = "Usuario recuperado con éxito.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDetailDTO.class)))
    @GetMapping("/id/{id}")
    public UserDetailDTO getUserById(@PathVariable Long id) {
        return usuarioService.getUsuarioDTOById(id);
    }

    /**
     * Crea un nuevo usuario en el sistema.
     * 
     * @param usuario Objeto `Usuarios` que contiene los detalles del nuevo usuario
     *                a crear.
     * @return El usuario recién creado con todos los detalles.
     */
    @Operation(summary = "Crear nuevo usuario", description = "Permite crear un nuevo usuario en el sistema.")
    @ApiResponse(responseCode = "201", description = "Usuario creado con éxito.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuarios.class)))
    @PostMapping("/create")
    public Usuarios createUsuario(@RequestBody Usuarios usuario) {
        return usuarioService.createUsuario(usuario);
    }

    /**
     * Elimina un usuario del sistema.
     * 
     * @param idUsuario ID del usuario a eliminar.
     * @return Respuesta vacía con código HTTP 204.
     */
    @Operation(summary = "Eliminar usuario", description = "Permite eliminar un usuario del sistema.")
    @ApiResponse(responseCode = "204", description = "Usuario eliminado con éxito.")
    @DeleteMapping("/delete/{idUsuario}")
    public void deleteUsuario(@PathVariable Long idUsuario) {
        usuarioService.deleteUsuario(idUsuario);
    }

    /**
     * Actualiza los detalles de un usuario existente en el sistema.
     * 
     * @param id      ID del usuario que se desea actualizar.
     * @param usuario Objeto `Usuarios` con los nuevos detalles para actualizar al
     *                usuario.
     * @return El usuario actualizado con todos los detalles.
     */
    @Operation(summary = "Actualizar usuario", description = "Permite actualizar los detalles de un usuario existente en el sistema.")
    @ApiResponse(responseCode = "200", description = "Usuario actualizado con éxito.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Usuarios.class)))
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado.", content = @Content(mediaType = "application/json"))
    @PutMapping("/update/{id}")
    public ResponseEntity<Usuarios> updateUsuario(@PathVariable Long id, @RequestBody Usuarios usuario) {
        Usuarios updatedUsuario = usuarioService.updateUsuario(id, usuario);
        if (updatedUsuario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Usuario no encontrado
        }
        return ResponseEntity.ok(updatedUsuario); // Usuario actualizado con éxito
    }

}
