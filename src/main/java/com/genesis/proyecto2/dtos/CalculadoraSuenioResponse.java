package com.genesis.proyecto2.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 12/04/2026
 * DTO que devuelve las opciones de horarios calculados para sueño (OP-04).
 * Corresponde al schema SleepCalculatorResponse de openapi.yaml.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CalculadoraSuenioResponse {

    /**
     * Lista de 3 opciones de horario:
     * Mínimo (4 ciclos / 6h), Recomendado (5 ciclos / 7.5h), Ideal (6 ciclos / 9h).
     */
    private List<OpcionSuenio> opciones;
}
