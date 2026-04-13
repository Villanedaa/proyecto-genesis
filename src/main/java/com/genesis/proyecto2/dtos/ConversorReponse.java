package com.genesis.proyecto2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 12/04/2026
 * DTO que contiene el resultado de la conversión de moneda y los datos de la tasa aplicada.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversorReponse {


    /**
     * Resultado de la conversion
     * */
    private Double montoConvertido;

    /**
     * Direccion de cambio sea cop-> usd o usd-> cop
     * */
    private String direccion;

    /**
     * Valor tasa de cambio
     * */
    private Double tasaAplicada;


    private LocalDateTime fechaActualizacionTasa;
}
