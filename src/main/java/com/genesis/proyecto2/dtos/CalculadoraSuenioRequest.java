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
 * DTO para solicitar el calculo de ciclos de suenio
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculadoraSuenioRequest {
    /**
     * Hora de referencia para el calculo
     * */
    private String horaReferencia;
    /**
     * DESPERTAR/ACOSTARSE
     * */
    private String modo;
}
