package com.genesis.proyecto2.dtos;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 12/04/2026
 * DTO que representa la solicitud de cálculo para una operación de crédito.
 * Contiene los parámetros básicos para determinar la viabilidad financiera.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditoRequest {

    /** Monto total a financiar. */
    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a cero")
    private Double precio;

    /** Número de cuotas para el pago del crédito. */
    @NotNull(message = "Las cuotas son obligatorias")
    @Positive(message = "Las cuotas deben ser mayor a cero")
    private Integer cuotas;

    /** Tasa de interés mensual expresada en porcentaje. */
    @NotNull(message = "La tasa mensual es obligatoria")
    @Positive(message = "La tasa mensual debe ser mayor a cero")
    private Double tasaMensual;
}
