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
 * DTO que transporta el token JWT tras una autenticación exitosa
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    /**
     * Token generado por JWT
     * */
    private String token;
    /**
     * Esquema de autenticaciOn utilizado. Por defecto es Bearer,
     * */
    private String tipoToken = "Bearer";
}
