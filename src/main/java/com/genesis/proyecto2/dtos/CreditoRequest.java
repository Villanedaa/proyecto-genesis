package com.genesis.proyecto2.dtos;


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

    /**
     * Monto total a financiar.
     */
    private Double precio;
    /**
     * Numero de cuotas para el pago del credito
     */
    private Integer cuotas;

    /**
     * Tasa de interes mensual expresada en porcentaje
     */

    private Double tasaMensual;
}
