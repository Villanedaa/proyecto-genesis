package com.genesis.proyecto2.controllers;

import com.genesis.proyecto2.dtos.*;
import com.genesis.proyecto2.services.IExecutionFacadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 12/04/2026
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

    private final IExecutionFacadeService executionFacade;

    /**
     * OP-01 — ¿Cuánto me cuesta ese crédito?
     * Calcula la cuota mensual y la tabla de amortización completa.
     * Costo base: 50 tokens.
     */
    @PostMapping("/credit-calculator")
    public ResponseEntity<CreditoResponse> calcularCredito(
            Authentication authentication,
            @Valid @RequestBody CreditoRequest request) {
        String email = authentication.getName();
        return ResponseEntity.ok(executionFacade.executeCreditCalculator(email, request));
    }

    /**
     * OP-02 — Conversor COP ↔ USD.
     * Usa la tasa de cambio vigente gestionada por el administrador.
     * Costo base: 20 tokens.
     */
    @PostMapping("/currency-converter")
    public ResponseEntity<ConversorResponse> convertirMoneda(
            Authentication authentication,
            @Valid @RequestBody ConversorRequest request) {
        String email = authentication.getName();
        return ResponseEntity.ok(executionFacade.executeCurrencyConverter(email, request));
    }

    /**
     * OP-03 — Calculadora de IMC.
     * Retorna IMC, categoría y rango de peso saludable.
     * Costo base: 15 tokens.
     */
    @PostMapping("/bmi-calculator")
    public ResponseEntity<ImcReponse> calcularImc(
            Authentication authentication,
            @Valid @RequestBody ImcRequest request) {
        String email = authentication.getName();
        return ResponseEntity.ok(executionFacade.executeBmiCalculator(email, request));
    }

    /**
     * OP-04 — Calculadora de sueño.
     * Genera 3 opciones de horario (Mínimo, Recomendado, Ideal).
     * Costo base: 20 tokens.
     */
    @PostMapping("/sleep-calculator")
    public ResponseEntity<CalculadoraSuenioResponse> calcularSuenio(
            Authentication authentication,
            @Valid @RequestBody CalculadoraSuenioRequest request) {
        String email = authentication.getName();
        return ResponseEntity.ok(executionFacade.executeSleepCalculator(email, request));
    }
}
