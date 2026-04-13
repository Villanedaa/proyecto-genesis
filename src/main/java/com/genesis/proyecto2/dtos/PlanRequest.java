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
 * DTO para la creación y edición de planes de suscripción.
 * Permite al administrador definir los beneficios de cada nivel.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanRequest {
    /**
     * Nombre del plan (Free, Pro, Enterprise)
     */

    private String nombre;

    /**
     * Cantidad de tokens que se acreditan al usuario tras la suscripcion
     */

    private Integer tokensOtorgados;
}
