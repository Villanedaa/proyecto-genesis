package com.genesis.proyecto2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

/**
 * @author Sergio<sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan<jhojana.villadav@autonoma.edu.co>
 * @author Sebastian<sebastian.villanedag@autonoma.edu.co
 * @version 1.0
 * @since 12/04/2026
 * DTO que captura la información necesaria para el registro de un nuevo usuario.
 * Este objeto transporta los datos desde la solicitud de registro hacia la lógica de negocio.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistroUsuario {

    /**
     *  Nombre unico elegido por el usuario
     */
    @NotNull
    private String nombreUsuario;

    /**
     * correo que se asocia a la cuenta del usuario
     */
    @NotNull
    private String correo;

    /**
     * Contrasenia elegida por el usuario
     */
    @NotNull
    private String contrasenia;
}