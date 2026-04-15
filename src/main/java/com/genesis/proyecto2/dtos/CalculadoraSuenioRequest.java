package com.genesis.proyecto2.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 12/04/2026
 * DTO para solicitar el cálculo de ciclos de sueño (OP-04).
 * Campos según el esquema SleepCalculatorRequest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculadoraSuenioRequest {

    /**
     * Modo de cálculo HORA_DESPERTAR o HORA_ACOSTARSE
     */
    @NotBlank(message = "El modo es obligatorio")
    @Pattern(regexp = "HORA_DESPERTAR|HORA_ACOSTARSE",
             message = "El modo debe ser HORA_DESPERTAR o HORA_ACOSTARSE")
    private String modo;

    /**
     * Hora de referencia en formato HH:mm
     */
    @NotBlank(message = "La hora de referencia es obligatoria")
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$",
             message = "La hora debe estar en formato HH:mm (ej: 07:30)")
    private String hora;

    /**
     * Minutos promedio que tarda el usuario en quedarse dormido
     * Por defecto 14 minutos
     */
    private Integer minutosDormir = 14;
}
