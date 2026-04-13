package com.genesis.proyecto2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 12/04/2026
 * DTO que contiene el resultado detallado del calculo de crédito,

 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditoResponse {
    /**
     * Valor fijo de la cuota mensual a pagar.
     */
    private Double cuotaMensual;

    /**
     * Sumatoria de todos los pagos realizados al finalizar el credito
     */
    private Double totalPagado;

    /**
     * Monto total pagado únicamente por intereses
     */
    private Double totalIntereses;

    /**
     * Listado  del comportamiento del crédito periodo tras periodo.
     */
    private List<DetalleCuota> tablaPagos;
}
