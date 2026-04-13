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
 * DTO para devolver la información de los roles sin exponer la entidad completa.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolResponse {
    private Long id;
    private String nombre;
}