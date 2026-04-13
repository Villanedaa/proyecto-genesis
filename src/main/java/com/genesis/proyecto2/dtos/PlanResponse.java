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
 * DTO que representa la información pública de un plan de suscripción.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanResponse {
    /**
     * Id unico del plan
     */
    private Long id;

    /**
     * Nombre del plan.
     */
    private String nombre;

    /**
     * Tokens que recibirá el usuario al suscribirse.
     */
    private Integer tokensOtorgados;
}
