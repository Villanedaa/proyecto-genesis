package com.genesis.proyecto2.controllers;

import com.genesis.proyecto2.dtos.RolReponse;
import com.genesis.proyecto2.entities.Rol;
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
 * Controlador para la exposición de los servicios del catálogo de roles.
 */
@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolController {

    private final IRolService rolService;

    @GetMapping
    public ResponseEntity<List<RolReponse>> findAll() {
        List<RolReponse> response = rolService.findAll().stream()
                .map(this::toResponse).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolReponse> findById(@PathVariable Long id) {
        return rolService.findById(id)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Mapeo manual para mantener el desacoplamiento de la entidad.
     */
    private RolReponse toResponse(Rol rol) {
        RolReponse response = new RolReponse();
        response.setId(rol.getId());
        response.setNombre(rol.getNombre());
        return response;
    }
}