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
 * DTO utilizado por el administrador para realizar recargas manuales de tokens a un usuario
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecargaPeticionManual {


    /**
     * Id del usuario a recargar tokens
     * */
    private Long usuarioId;
    /**
     * Cantidad de tokens a recargar
     * */
    private Integer cantidadTokens;
}
