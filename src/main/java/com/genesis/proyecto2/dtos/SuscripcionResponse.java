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
 * DTO que confirma el resultado de una nueva suscripción o cambio de plan.

 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuscripcionResponse {

    private Long id;
    private Long usuarioId;
    private String nombrePlan; // PREMIUM, BASIC
    private Double costo;
    private String estado; // ACTIVA
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
}
