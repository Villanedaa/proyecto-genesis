package com.genesis.proyecto2.controllers;

import com.genesis.proyecto2.dtos.RegistroUsuario;
import com.genesis.proyecto2.dtos.UsuarioResponse;
import com.genesis.proyecto2.services.IUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para la gestión de usuarios y perfiles en el sistema.
 */
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final IUsuarioService usuarioService;

    /**
     * Endpoint para el registro de nuevos usuarios (Punto de entrada del sistema).
     */
    @PostMapping("/registro")
    public ResponseEntity<UsuarioResponse> registrar(@Valid @RequestBody RegistroUsuario request) {
        // El service se encarga de convertir el DTO a Entity y viceversa
        UsuarioResponse response = usuarioService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> obtenerPerfil(@PathVariable Long id) {
        return usuarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarTodos() {
        return ResponseEntity.ok(usuarioService.findAll());
    }
}