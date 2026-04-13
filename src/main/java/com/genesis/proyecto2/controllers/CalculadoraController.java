package com.genesis.proyecto2.controllers;

import com.genesis.proyecto2.dtos.*;
import com.genesis.proyecto2.services.ICalculadoraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para la ejecución de las operaciones del catálogo Genesis.
 * Rutas según openapi.yaml:
 *   POST /execute/credit-calculator    → OP-01 (costo base: 50 tokens)
 *   POST /execute/currency-converter   → OP-02 (costo base: 20 tokens)
 *   POST /execute/bmi-calculator       → OP-03 (costo base: 15 tokens)
 *   POST /execute/sleep-calculator     → OP-04 (costo base: 20 tokens)
 */
@RestController
@RequestMapping("/execute")
@RequiredArgsConstructor
public class CalculadoraController {

    private final ICalculadoraService calculadoraService;

    /**
     * OP-01 — ¿Cuánto me cuesta ese crédito?
     * Calcula la cuota mensual y la tabla de amortización completa.
     * Costo base: 50 tokens.
     */
    @PostMapping("/credit-calculator")
    public ResponseEntity<CreditoResponse> calcularCredito(
            @Valid @RequestBody CreditoRequest request) {
        return ResponseEntity.ok(calculadoraService.calcularCredito(request));
    }

    /**
     * OP-02 — Conversor COP ↔ USD.
     * Usa la tasa de cambio vigente gestionada por el administrador.
     * Costo base: 20 tokens.
     */
    @PostMapping("/currency-converter")
    public ResponseEntity<ConversorResponse> convertirMoneda(
            @Valid @RequestBody ConversorRequest request) {
        return ResponseEntity.ok(calculadoraService.convertirMoneda(request));
    }

    /**
     * OP-03 — Calculadora de IMC.
     * Retorna IMC, categoría y rango de peso saludable.
     * Costo base: 15 tokens.
     */
    @PostMapping("/bmi-calculator")
    public ResponseEntity<ImcReponse> calcularImc(
            @Valid @RequestBody ImcRequest request) {
        return ResponseEntity.ok(calculadoraService.calcularImc(request));
    }

    /**
     * OP-04 — Calculadora de sueño.
     * Genera 3 opciones de horario (Mínimo, Recomendado, Ideal).
     * Costo base: 20 tokens.
     */
    @PostMapping("/sleep-calculator")
    public ResponseEntity<CalculadoraSuenioResponse> calcularSuenio(
            @Valid @RequestBody CalculadoraSuenioRequest request) {
        return ResponseEntity.ok(calculadoraService.calcularSuenio(request));
    }
}
