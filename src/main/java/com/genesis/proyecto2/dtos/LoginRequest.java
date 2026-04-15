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
 * DTO para capturar las credenciales de inicio de sesion
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String nombreUsuario;
    private String contrasenia;
}
