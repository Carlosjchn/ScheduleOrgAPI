package com.jpa1prueba.jpademo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpa1prueba.jpademo.dto.equipo.EquipoBasicDTO;
import com.jpa1prueba.jpademo.dto.equipo.EquipoDetailDTO;
import com.jpa1prueba.jpademo.entities.Equipos;

import com.jpa1prueba.jpademo.services.EquiposService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("api/equipos")
public class EquiposController {

    @Autowired
    private EquiposService equiposService;

    /**
     * Obtiene una lista de todos los equipos registrados en el sistema.
     * 
     * @return Lista de objetos `EquipoBasicDTO`, que contiene la información básica
     *         de cada equipo.
     */
    @Operation(summary = "Obtener todos los equipos", description = "Devuelve una lista con todos los equipos registrados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Equipos obtenidos exitosamente.", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EquipoBasicDTO.class))))
    @GetMapping("/all")
    public List<EquipoBasicDTO> getAllEquipos() {
        return equiposService.getAllEquipos();
    }

    /**
     * Obtiene los detalles de un equipo basado en su nombre.
     * 
     * @param nombre Nombre del equipo que se busca.
     * @return Objeto `EquipoDetailDTO` que contiene la información detallada del
     *         equipo.
     */
    @Operation(summary = "Obtener equipo por nombre", description = "Devuelve los detalles de un equipo utilizando su nombre.")
    @Parameter(name = "nombre", description = "Nombre del equipo", required = true)
    @ApiResponse(responseCode = "200", description = "Equipo encontrado con éxito.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EquipoDetailDTO.class)))
    @GetMapping("/nombre/{nombre}")
    public EquipoDetailDTO getByNombre(@PathVariable String nombre) {
        return equiposService.getByNombre(nombre);
    }

    /**
     * Obtiene los detalles de un equipo mediante su ID.
     * 
     * @param idEquipo ID único del equipo que se busca.
     * @return Objeto `Equipos` que contiene la información completa del equipo.
     */
    @Operation(summary = "Obtener equipo por ID", description = "Devuelve los detalles de un equipo utilizando su ID.")
    @Parameter(name = "idEquipo", description = "ID del equipo", required = true)
    @ApiResponse(responseCode = "200", description = "Equipo encontrado con éxito.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Equipos.class)))
    @GetMapping("/id/{idEquipo}")
    public Equipos getEquipo(@PathVariable Long idEquipo) {
        return equiposService.getEquipoById(idEquipo);
    }

    /**
     * Crea un nuevo equipo en el sistema.
     * 
     * @param equipo Objeto `Equipos` que contiene los datos del nuevo equipo.
     * @return El equipo recién creado con todos los detalles.
     */
    @Operation(summary = "Crear nuevo equipo", description = "Permite crear un nuevo equipo en el sistema.")
    @ApiResponse(responseCode = "201", description = "Equipo creado con éxito.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Equipos.class)))
    @PostMapping("/crear")
    public Equipos createEquipo(@RequestBody Equipos equipo) {
        return equiposService.createEquipo(equipo);
    }

    /**
     * Elimina un equipo existente basado en su ID.
     * 
     * @param idEquipo ID único del equipo a eliminar.
     */
    @Operation(summary = "Eliminar equipo", description = "Permite eliminar un equipo existente utilizando su ID.")
    @Parameter(name = "idEquipo", description = "ID del equipo", required = true)
    @ApiResponse(responseCode = "200", description = "Equipo eliminado con éxito.")
    @DeleteMapping("/borrar/{idEquipo}")
    public void deleteEquipo(@PathVariable Long idEquipo) {
        equiposService.deleteEquipo(idEquipo);
    }

    /**
     * Actualiza un equipo existente.
     * 
     * @param idEquipo ID del equipo a actualizar.
     * @param equipo   Objeto `Equipos` con los nuevos detalles para actualizar.
     * @return El equipo actualizado.
     */
    @Operation(summary = "Actualizar equipo", description = "Permite actualizar un equipo existente en el sistema.")
    @ApiResponse(responseCode = "200", description = "Equipo actualizado con éxito.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Equipos.class)))
    @PutMapping("/actualizar/{idEquipo}")
    public ResponseEntity<Equipos> updateEquipo(@PathVariable Long idEquipo, @RequestBody Equipos equipo) {
        Equipos updatedEquipo = equiposService.updateEquipo(idEquipo, equipo);
        return ResponseEntity.ok(updatedEquipo);
    }

}
