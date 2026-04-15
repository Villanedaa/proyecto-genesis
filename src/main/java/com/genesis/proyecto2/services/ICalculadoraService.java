package com.genesis.proyecto2.services;

import com.genesis.proyecto2.dtos.CreditoRequest;
import com.genesis.proyecto2.dtos.CreditoResponse;
import com.genesis.proyecto2.dtos.ConversorRequest;
import com.genesis.proyecto2.dtos.ConversorResponse;
import com.genesis.proyecto2.dtos.ImcRequest;
import com.genesis.proyecto2.dtos.ImcReponse;
import com.genesis.proyecto2.dtos.CalculadoraSuenioRequest;
import com.genesis.proyecto2.dtos.CalculadoraSuenioResponse;

/**
 * Interfaz de servicio para las cuatro operaciones de cómputo del catálogo
 * Siguiendo principio de Responsabilidad Única, cada método corresponde a una operación.
 * La lógica de descuento de tokens NO vive aquí; la gestiona el servicio de ejecución.
 */
public interface ICalculadoraService {

    /**
     * OP-01 — Calculadora de crédito
     * Calcula cuota mensual, total pagado, total en intereses y tabla de amortitización
     *
     * @param request parámetros de entrada precio cuotas tasa mensual
     * @return respuesta con tabla de amortización incluida
     */
    CreditoResponse calcularCredito(CreditoRequest request);

    /**
     * OP-02 — Conversor COP USD
     * Usa la tasa de cambio vigente almacenada en la tabla tasa_cambio
     *
     * @param request monto y moneda de origen
     * @return monto convertido dirección tasa aplicada y fecha de la tasa
     */
    ConversorResponse convertirMoneda(ConversorRequest request);

    /**
     * OP-03 — Calculadora de IMC
     * Calcula IMC, categoría y rango de peso saludable.
     *
     * @param request peso ( y altura 
     * @return IMC, categoría, pesos mínimo/máximo saludables y diferencia al rango
     */
    ImcReponse calcularImc(ImcRequest request);

    /**
     * OP-04 — Calculadora de sueño.
     * Genera 3 opciones de horario (4, 5 y 6 ciclos de 90 min).
     *
     * @param request modo (HORA_DESPERTAR / HORA_ACOSTARSE), hora y minutos para dormir
     * @return lista de 3 opciones: Mínimo, Recomendado e Ideal
     */
    CalculadoraSuenioResponse calcularSuenio(CalculadoraSuenioRequest request);
}
