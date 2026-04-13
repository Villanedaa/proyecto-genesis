package com.genesis.proyecto2.exception;

/**
 * Se lanza cuando una acción de suscripción no es válida a nivel de lógica de negocio —
 * por ejemplo si el usuario intenta suscribirse al mismo plan que
 * ya tiene o cuando no se proporciona un plan válido
 * <p>Mapea al código HTTP 400 Bad Request (openapi.yaml: POST /users/me/subscribe
 * → 400 "Plan inválido o error de suscripción").</p>
 *
 * <p>Ejemplo de uso:</p>
 * <pre>
 * if (usuario.getPlan() != null
 * && usuario.getPlan().getId().equals(request.planId())) {
 * throw new InvalidSubscriptionException(
 * "Ya estás suscrito al plan seleccionado.");
 * }
 * </pre>
 */
public class InvalidSubscriptionException extends BusinessException {

    public InvalidSubscriptionException(String message) {
        super(message);
    }
}
