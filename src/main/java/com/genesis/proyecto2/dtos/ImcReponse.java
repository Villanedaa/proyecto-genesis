package com.genesis.proyecto2.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 12/04/2026
 * DTO que devuelve el resultado completo del cálculo de IMC (OP-03).
 * Campos según el esquema BMICalculatorResponse de openapi.yaml.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImcReponse {

    /** Valor del IMC redondeado a dos decimales. */
    private Double imc;

    /** Categoría: "Bajo peso", "Peso normal", "Sobrepeso" u "Obesidad". */
    private String categoria;

    /** Peso mínimo para estar en rango saludable (kg). */
    private Double pesoMinimoSaludable;

    /** Peso máximo para estar en rango saludable (kg). */
    private Double pesoMaximoSaludable;

    /**
     * Diferencia entre el peso actual y el rango saludable.
     * Negativo = por debajo del mínimo, positivo = por encima del máximo, 0 = en rango.
     */
    private Double diferenciaRango;
}
