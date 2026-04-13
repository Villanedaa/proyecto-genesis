package com.genesis.proyecto2.services.impl;

import com.genesis.proyecto2.dtos.*;
import com.genesis.proyecto2.exception.ResourceNotFoundException;
import com.genesis.proyecto2.repositories.ITasaCambioRepository;
import com.genesis.proyecto2.services.ICalculadoraService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación de las cuatro operaciones de cómputo del catálogo Genesis.
 *
 * <p>Principios aplicados:</p>
 * <ul>
 *   <li><b>SRP</b>: esta clase solo realiza los cálculos matemáticos, no maneja tokens ni seguridad.</li>
 *   <li><b>OCP</b>: agregar una nueva operación implica añadir un método, no modificar los existentes.</li>
 *   <li><b>DIP</b>: depende de la interfaz {@link ICalculadoraService}, no de detalles concretos.</li>
 * </ul>
 */
@Service
@RequiredArgsConstructor
public class CalculadoraServiceImpl implements ICalculadoraService {

    private final ITasaCambioRepository tasaCambioRepository;

    // ── OP-01: Calculadora de Crédito ─────────────────────────────────────────

    /**
     * Fórmula según el proyecto Genesis:
     * i = tasa_mensual / 100
     * cuota = precio × (i × (1+i)^cuotas) / ((1+i)^cuotas - 1)
     * total_pagado = cuota × cuotas
     * total_intereses = total_pagado - precio
     */
    @Override
    @Transactional(readOnly = true)
    public CreditoResponse calcularCredito(CreditoRequest request) {
        double precio    = request.getPrecio();
        int    cuotas    = request.getCuotas();
        double i         = request.getTasaMensual() / 100.0;

        // Factor (1+i)^cuotas
        double factor = Math.pow(1 + i, cuotas);

        // Cuota mensual con la fórmula de amortización francesa
        double cuotaMensual  = precio * (i * factor) / (factor - 1);
        double totalPagado   = cuotaMensual * cuotas;
        double totalIntereses = totalPagado - precio;

        // Tabla de amortización cuota a cuota
        List<DetalleCuota> tabla = new ArrayList<>();
        double saldo = precio;
        for (int periodo = 1; periodo <= cuotas; periodo++) {
            double interes  = saldo * i;
            double capital  = cuotaMensual - interes;
            saldo          -= capital;

            tabla.add(new DetalleCuota(
                    periodo,
                    redondear(capital),
                    redondear(interes),
                    redondear(Math.max(saldo, 0.0))   // evitar -0.00 por flotantes
            ));
        }

        return new CreditoResponse(
                redondear(cuotaMensual),
                redondear(totalPagado),
                redondear(totalIntereses),
                tabla
        );
    }

    // ── OP-02: Conversor COP ↔ USD ────────────────────────────────────────────

    /**
     * Fórmula según el proyecto Genesis:
     * Si moneda_origen = COP → resultado = monto / tasa_cop_por_usd
     * Si moneda_origen = USD → resultado = monto × tasa_cop_por_usd
     */
    @Override
    @Transactional(readOnly = true)
    public ConversorResponse convertirMoneda(ConversorRequest request) {
        var tasaCambio = tasaCambioRepository.findLatest()
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No hay una tasa de cambio COP/USD registrada en el sistema."));

        double tasa   = tasaCambio.getValorCopPorUsd().doubleValue();
        double monto  = request.getMonto();
        String origen = request.getMonedaOrigen().toUpperCase();

        double resultado;
        String direccion;

        if ("COP".equals(origen)) {
            resultado  = monto / tasa;
            direccion  = "COP → USD";
        } else {
            resultado  = monto * tasa;
            direccion  = "USD → COP";
        }

        ConversorResponse response = new ConversorResponse();
        response.setMontoConvertido(redondear(resultado));
        response.setDireccion(direccion);
        response.setTasaAplicada(tasa);
        response.setFechaActualizacionTasa(tasaCambio.getFechaActualizacion());
        return response;
    }

    // ── OP-03: Calculadora de IMC ─────────────────────────────────────────────

    /**
     * Fórmulas según el proyecto Genesis:
     * altura_m = altura_cm / 100
     * IMC = peso_kg / altura_m²
     * peso_min = 18.5 × altura_m²
     * peso_max = 24.9 × altura_m²
     */
    @Override
    @Transactional(readOnly = true)
    public ImcReponse calcularImc(ImcRequest request) {
        double alturaM  = request.getAlturaCm() / 100.0;
        double imc      = request.getPesoKg() / (alturaM * alturaM);
        double pesoMin  = 18.5 * alturaM * alturaM;
        double pesoMax  = 24.9 * alturaM * alturaM;

        String categoria;
        if      (imc < 18.5)  categoria = "Bajo peso";
        else if (imc < 25.0)  categoria = "Peso normal";
        else if (imc < 30.0)  categoria = "Sobrepeso";
        else                  categoria = "Obesidad";

        // Diferencia con el rango saludable:
        // negativo = por debajo del mínimo, positivo = por encima del máximo, 0 = en rango
        double diferencia;
        if      (request.getPesoKg() < pesoMin) diferencia = redondear(request.getPesoKg() - pesoMin);
        else if (request.getPesoKg() > pesoMax) diferencia = redondear(request.getPesoKg() - pesoMax);
        else                                     diferencia = 0.0;

        return ImcReponse.builder()
                .imc(redondear(imc))
                .categoria(categoria)
                .pesoMinimoSaludable(redondear(pesoMin))
                .pesoMaximoSaludable(redondear(pesoMax))
                .diferenciaRango(diferencia)
                .build();
    }

    // ── OP-04: Calculadora de Sueño ───────────────────────────────────────────

    /**
     * Un ciclo = 90 minutos. Se calculan 3 opciones: 4, 5 y 6 ciclos.
     *
     * Modo HORA_DESPERTAR:
     *   hora_acostarse = hora_despertar - (ciclos × 90 min) - minutos_para_dormir
     *
     * Modo HORA_ACOSTARSE:
     *   hora_despertar = hora_acostarse + minutos_para_dormir + (ciclos × 90 min)
     */
    @Override
    @Transactional(readOnly = true)
    public CalculadoraSuenioResponse calcularSuenio(CalculadoraSuenioRequest request) {
        DateTimeFormatter fmt     = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime horaRef         = LocalTime.parse(request.getHora(), fmt);
        int minutosDormir         = request.getMinutosDormir() != null ? request.getMinutosDormir() : 14;
        boolean esDespertar       = "HORA_DESPERTAR".equals(request.getModo());

        int[]    ciclos     = {4, 5, 6};
        String[] etiquetas  = {"Mínimo", "Recomendado", "Ideal"};

        List<OpcionSuenio> opciones = new ArrayList<>();
        for (int i = 0; i < ciclos.length; i++) {
            int totalMinutos = ciclos[i] * 90;

            LocalTime horaCalculada;
            if (esDespertar) {
                // Retrocedemos: hora de acostarse = despertar - ciclos - minutos para dormir
                horaCalculada = horaRef
                        .minusMinutes(totalMinutos)
                        .minusMinutes(minutosDormir);
            } else {
                // Avanzamos: hora de despertar = acostarse + minutos para dormir + ciclos
                horaCalculada = horaRef
                        .plusMinutes(minutosDormir)
                        .plusMinutes(totalMinutos);
            }

            opciones.add(OpcionSuenio.builder()
                    .horaCalculada(horaCalculada.format(fmt))
                    .horasTotalesSueno(ciclos[i] * 1.5)   // 90 min = 1.5 h
                    .etiquetaCalidad(etiquetas[i])
                    .build());
        }

        return CalculadoraSuenioResponse.builder()
                .opciones(opciones)
                .build();
    }

    // ── Utilidad ──────────────────────────────────────────────────────────────

    /** Redondea a 2 decimales para evitar ruido de punto flotante en las respuestas. */
    private double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
