package com.genesis.proyecto2.services.impl;

import com.genesis.proyecto2.dtos.OperacionResponse;
import com.genesis.proyecto2.exception.ResourceNotFoundException;
import com.genesis.proyecto2.repositories.IOperacionRepository;
import com.genesis.proyecto2.services.IOperacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OperacionServiceImpl implements IOperacionService {

    private final IOperacionRepository operacionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<OperacionResponse> findAllActive() {
        // Filtramos por el campo 'estado' que tienes en tu Entity
        return operacionRepository.findAll().stream()
                .filter(op -> "ACTIVO".equals(op.getEstado()))
                .map(op -> OperacionResponse.builder()
                        .id(op.getId())
                        .nombre(op.getNombre()) // Usamos 'nombre' de tu Entity
                        .descripcion("Código: " + op.getCodigo()) // Usamos 'codigo'
                        .costoToken(java.math.BigDecimal.valueOf(op.getCostoBase())) // Usamos 'costoBase'
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public OperacionResponse findById(Long id) {
        return operacionRepository.findById(id)
                .map(op -> OperacionResponse.builder()
                        .id(op.getId())
                        .nombre(op.getNombre())
                        .costoToken(java.math.BigDecimal.valueOf(op.getCostoBase()))
                        .build())
                .orElseThrow(() -> new ResourceNotFoundException("Operacion", id));
    }

    @Override
    public boolean existsById(Long id) {
        return operacionRepository.existsById(id);
    }
}