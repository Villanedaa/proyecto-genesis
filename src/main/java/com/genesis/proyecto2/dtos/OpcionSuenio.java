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
 * Representa una opción de horario de sueño calculada (OP-04)
 * Corresponde al schema SleepOption
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OpcionSuenio {

    /** Hora calculada en formato HH:mm */
    private String horaCalculada;

    /** Total de horas de sueño que corresponden a este ciclo */
    private Double horasTotalesSueno;

    /**
     * Etiqueta de calidad del ciclo.
     * Valores posibles: Mínimo 4 ciclos, Recomendado 5 ciclos, Ideal 6 ciclos
     */
    private String etiquetaCalidad;
}
