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
 * DTO para la solicitud de conversión de moneda entre COP y USD
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversorRequest {
    /**
     * Valor a convertir
     */
    private Double monto;
    /**
     * Moneda de origen de la transaccion (COP o USD).
     */

    private String monedaOrigen;
}
