package com.genesis.proyecto2.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 12/04/2026
 * DTO para la solicitud de conversión de moneda entre COP y USD
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversorRequest {
    /** Valor a convertir. Debe ser mayor a cero. */
    @NotNull(message = "El monto es obligatorio")
    @Positive(message = "El monto debe ser mayor a cero")
    private Double monto;

    /** Moneda de origen: COP o USD. */
    @NotBlank(message = "La moneda de origen es obligatoria")
    @Pattern(regexp = "COP|USD", message = "La moneda de origen debe ser COP o USD")
    private String monedaOrigen;
}
