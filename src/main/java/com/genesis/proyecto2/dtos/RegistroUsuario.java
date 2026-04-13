package com.genesis.proyecto2.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
     * Nombre único elegido por el usuario
     */
    @NotBlank(message = "El nombre de usuario es obligatorio")
    private String nombreUsuario;

    /**
     * Correo que se asocia a la cuenta del usuario
     */
    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El correo debe tener un formato válido")
    private String correo;

    /**
     * Contraseña elegida por el usuario
     */
    @NotBlank(message = "La contraseña es obligatoria")
    private String contrasenia;
}