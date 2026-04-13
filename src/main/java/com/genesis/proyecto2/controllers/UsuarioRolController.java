package com.genesis.proyecto2.controllers;

import com.genesis.proyecto2.dtos.UsuarioRolCrearRequest;
import com.genesis.proyecto2.dtos.UsuarioRolResponse;
import com.genesis.proyecto2.services.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario-roles")
@RequiredArgsConstructor
public class UsuarioRolController {

    private final IUsuarioService usuarioService;

    @PostMapping("/asignar")
    public ResponseEntity<UsuarioRolResponse> asignarRol(@RequestBody UsuarioRolCrearRequest request) {
        return ResponseEntity.ok(usuarioService.asignarRol(request));
    }
}