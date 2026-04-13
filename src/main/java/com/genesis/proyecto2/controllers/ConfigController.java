package com.genesis.proyecto2.controllers;

import com.genesis.proyecto2.dtos.UpdateExchangeRateRequest;
import com.genesis.proyecto2.entities.TasaCambio;
import com.genesis.proyecto2.repositories.ITasaCambioRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/config")
@RequiredArgsConstructor
public class ConfigController {

    private final ITasaCambioRepository tasaCambioRepository;

    @PutMapping("/exchange-rate")
    public ResponseEntity<Void> updateExchangeRate(@Valid @RequestBody UpdateExchangeRateRequest request) {
        TasaCambio nuevaTasa = new TasaCambio();
        nuevaTasa.setValorCopPorUsd(BigDecimal.valueOf(request.getCopPerUsd()));
        // fechaActualizacion es gestionada por pre-persist o similar o current time
        // En TasaCambio Entity `fechaActualizacion` tiene insertable = false, updatable = false
        // That means DB manages it so we just save a new record.
        tasaCambioRepository.save(nuevaTasa);
        return ResponseEntity.ok().build();
    }
}
