package com.genesis.proyecto2.controllers;

import com.genesis.proyecto2.dtos.UsuarioRolCrearRequest;
import com.genesis.proyecto2.dtos.UsuarioResponse; // Usamos tu DTO existente
import com.genesis.proyecto2.services.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 12/04/2026
 * Controlador para gestionar la asignacion de permisos y roles a los usuarios
 */
@RestController
@RequestMapping("/api/usuario-roles")
@RequiredArgsConstructor
public class UsuarioRolController {

    private final IUsuarioService usuarioService;

    /**
     * Asigna un rol a un usuario y retorna el perfil del usuario actualizado.
     * Esto permite confirmar que el cambio se reflejó en su cuenta.
     */
    @PostMapping("/asignar")
    public ResponseEntity<UsuarioResponse> asignarRol(@RequestBody UsuarioRolCrearRequest request) {
        // El servicio procesa la unión y retorna el UsuarioResponse que ya definiste
        return ResponseEntity.ok(usuarioService.asignarRol(request));
    }
}