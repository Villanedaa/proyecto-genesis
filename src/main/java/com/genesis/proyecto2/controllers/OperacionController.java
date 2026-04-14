package com.genesis.proyecto2.controllers;

import com.genesis.proyecto2.dtos.OperacionResponse;
import com.genesis.proyecto2.services.IOperacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 12/04/2026
 */
@RestController
@RequestMapping("/api/operaciones")
@RequiredArgsConstructor
public class OperacionController {

    private final IOperacionService operacionService;

    /**
     * Consulta el catálogo de operaciones disponibles para el usuario.
     */
    @GetMapping
    public ResponseEntity<List<OperacionResponse>> listarCatalogo() {
        return ResponseEntity.ok(operacionService.findAllActive());
    }
}
