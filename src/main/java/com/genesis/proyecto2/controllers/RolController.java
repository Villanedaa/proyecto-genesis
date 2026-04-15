package com.genesis.proyecto2.controllers;

import com.genesis.proyecto2.dtos.RolResponse;
import com.genesis.proyecto2.entities.Rol;
import com.genesis.proyecto2.exception.ResourceNotFoundException;
import com.genesis.proyecto2.services.IRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 12/04/2026
 */
@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolController {

    private final IRolService rolService;

    @GetMapping
    public ResponseEntity<List<RolResponse>> findAll() {
        List<RolResponse> response = rolService.findAll().stream()
                .map(this::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolResponse> findById(@PathVariable Long id) {
        Rol rol = rolService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rol", id));
        return ResponseEntity.ok(toResponse(rol));
    }

    /**
     * Mapeo manual de Entidad a DTO.
     * Asegurarse de que RolResponse tenga los métodos setId y setNombre.
     */
    private RolResponse toResponse(Rol rol) {
        RolResponse response = new RolResponse();
        response.setId(rol.getId());
        response.setNombre(rol.getNombre());
        return response;
    }
}