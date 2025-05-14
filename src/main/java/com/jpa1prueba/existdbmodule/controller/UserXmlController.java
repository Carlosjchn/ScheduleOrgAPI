package com.jpa1prueba.existdbmodule.controller;

import com.jpa1prueba.existdbmodule.service.UserXmlService;
import com.jpa1prueba.jpademo.dto.user.UserDetailDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/xml")
public class UserXmlController {

    private final UserXmlService userXmlService;

    public UserXmlController(UserXmlService userXmlService) {
        this.userXmlService = userXmlService;
    }

    @Operation(summary = "Almacenar usuario en XML", description = "Almacena la información de un usuario en formato XML")
    @ApiResponse(responseCode = "200", description = "Usuario almacenado exitosamente")
    @PostMapping("/store/{userId}")
    public ResponseEntity<String> storeUser(@PathVariable Long userId) {
        try {
            userXmlService.saveUserToXml(userId);
            return ResponseEntity.ok("Usuario almacenado exitosamente en XML");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al almacenar usuario: " + e.getMessage());
        }
    }

    @Operation(summary = "Obtener usuario desde XML", description = "Recupera la información de un usuario desde el almacenamiento XML")
    @ApiResponse(responseCode = "200", description = "Usuario recuperado exitosamente")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailDTO> getUser(@PathVariable Long userId) {
        try {
            UserDetailDTO user = userXmlService.getUserFromXml(userId);
            return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Obtener todos los usuarios almacenados", description = "Recupera todos los documentos de usuarios almacenados")
    @ApiResponse(responseCode = "200", description = "Usuarios recuperados exitosamente")
    @GetMapping("/all")
    public ResponseEntity<Map<String, String>> getAllUsers() {
        try {
            return ResponseEntity.ok(userXmlService.getAllStoredUsers());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Eliminar todos los usuarios almacenados", description = "Elimina todos los documentos de usuarios almacenados")
    @ApiResponse(responseCode = "200", description = "Todos los usuarios eliminados exitosamente")
    @DeleteMapping("/clear")
    public ResponseEntity<String> clearAllUsers() {
        try {
            userXmlService.clearAllStoredUsers();
            return ResponseEntity.ok("Todos los usuarios almacenados han sido eliminados exitosamente");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al eliminar usuarios: " + e.getMessage());
        }
    }
}