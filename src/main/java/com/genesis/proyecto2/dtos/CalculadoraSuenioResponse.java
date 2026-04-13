package com.genesis.proyecto2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 12/04/2026
 * DTO que devuelve las distintas opciones de horarios para dormir o despertar.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculadoraSuenioResponse {
    private List<String> horariosSugeridos;
    private String mensaje;
}
