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
 * Representa el desglose detallado de una cuota individual dentro del plan de pagos
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalleCuota {
    /**
     * Número secuencial del mes o periodo de pago.
     */
    private Integer periodo;

    /**
     * Monto de la cuota que se abona directamente al capital de la deuda.
     */
    private Double capitalAmortizado;

    /**
     * Monto de la cuota destinado al pago de intereses generados en el periodo.
     */
    private Double interesPagado;

    /**
     * Saldo de la deuda que queda pendiente tras realizar el pago de este periodo.
     */
    private Double saldoRestante;
}
