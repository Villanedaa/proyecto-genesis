package com.genesis.proyecto2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Sergio<sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan<jhojana.villadav@autonoma.edu.co>
 * @author Sebastian<sebastian.villanedag@autonoma.edu.co
 * @version 1.0
 * @since 12/04/2026
 * DTO que representa la respuesta con la información de perfil de un usuario.
 * Se utiliza para devolver datos de forma segura hacia el frontend, excluyendo
 * información sensible como contraseñas.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {

    /**
     * Id unico del usuario
     */
    private Long id;

    /**
     * Nombre unico elegido por el usuario
     */
    private String nombreUsuario;

    /**
     * Dirección de correo electrónico del usuario
     */
    private String correo;

    /**
     * Cantidad actual de tokens disponibles
     */
    private Integer saldoTokens;

    /**
     * Estado del usuario ACTIVO, INACTIVO
     */
    private String estado;
    /**
     * Fecha de registro del usuario
     * */
    private LocalDateTime fechaCreacion;
}