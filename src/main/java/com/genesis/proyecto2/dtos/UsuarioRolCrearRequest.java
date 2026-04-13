package com.genesis.proyecto2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sergio<sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan<jhojana.villadav@autonoma.edu.co>
 * @author Sebastian<sebastian.villanedag@autonoma.edu.co
 * @version 1.0
 * @since 12/04/2026
 * Clase que representa la solicitud para crear una nueva relación entre un usuario y un rol.
 * Se utiliza en las funciones administrativas para gestionar los permisos del sistema.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRolCrearRequest {

    /**
     * Id unico del usuario
     */

    private Long usuarioId;

    /**
     * Id del rol( 1 ROLE_USER, 2 para ROLE_ADMIN).
     */

    private Long rolId;
}