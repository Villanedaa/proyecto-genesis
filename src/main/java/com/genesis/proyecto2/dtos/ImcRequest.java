package com.genesis.proyecto2.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 12/04/2026
 * DTO para la entrada de datos de la calculadora de Índice de Masa Corporal.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImcRequest {

    /** Peso del usuario en kilogramos */
    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser mayor a cero")
    private Double pesoKg;

    /** Altura del usuario en centímetros */
    @NotNull(message = "La altura es obligatoria")
    @Positive(message = "La altura debe ser mayor a cero")
    private Double alturaCm;
}
