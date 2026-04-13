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
 * DTO para solicitar la suscripciom a un plan específico del catlogo
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuscripcionRequest {
    /**
     * ID del plan al que el usuario desea suscribirse
     */
    private Long planId;
}
