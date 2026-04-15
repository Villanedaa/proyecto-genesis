package com.genesis.proyecto2.exception;

import java.util.List;

/**
 * Cuerpo de respuesta de error estándar definido en el esquema ErrorResponse de openapi.yaml.
 * Utilizado por el GlobalExceptionHandler para producir estructuras de error consistentes.
 */
public record ErrorResponse(
        String message,
        List<FieldError> details
) {

    /**
     * Un detalle de validación a nivel de campo individual.
     */
    public record FieldError(String field, String error) {}

    // ── Ayudantes de fábrica (Factory helpers) ───────────────────────────────────────

    /** Error de un solo campo validación regla de negocio en un campo específico. */
    public static ErrorResponse of(String message, String field, String error) {
        return new ErrorResponse(message, List.of(new FieldError(field, error)));
    }

    /** Error general sin detalles de campo por Ej entidad no encontrada, prohibido. */
    public static ErrorResponse of(String message) {
        return new ErrorResponse(message, List.of());
    }

    /** Múltiples errores de campo validación de beans. */
    public static ErrorResponse of(String message, List<FieldError> details) {
        return new ErrorResponse(message, details);
    }
}