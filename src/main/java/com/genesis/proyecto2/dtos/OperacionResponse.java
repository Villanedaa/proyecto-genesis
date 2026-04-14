package com.genesis.proyecto2.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 12/04/2026
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OperacionResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal costoToken; // El precio que se descontará del saldo
}