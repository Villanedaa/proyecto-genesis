package com.genesis.proyecto2.controllers;

import com.genesis.proyecto2.dtos.OperacionResponse;
import com.genesis.proyecto2.dtos.UpdateStatusRequest;
import com.genesis.proyecto2.entities.Operacion;
import com.genesis.proyecto2.exception.ResourceNotFoundException;
import com.genesis.proyecto2.repositories.IOperacionRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;

@RestController
@RequestMapping("/operations")
@RequiredArgsConstructor
public class OperationsController {

    private final IOperacionRepository operacionRepository;

    @GetMapping
    public ResponseEntity<List<OperacionResponse>> getAllOperations() {
        List<OperacionResponse> operations = operacionRepository.findAll().stream().map(o -> {
            OperacionResponse res = new OperacionResponse();
            res.setId(o.getId());
            res.setNombre(o.getNombre());
            res.setDescripcion(o.getCodigo()); //put code in description since DTO doesn't have it
            res.setCostoToken(BigDecimal.valueOf(o.getCostoBase()));
            return res;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(operations);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateOperationStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateStatusRequest request) {
        Operacion operacion = operacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Operacion", id));
        operacion.setEstado(request.getStatus());
        operacionRepository.save(operacion);
        return ResponseEntity.ok().build();
    }
}
