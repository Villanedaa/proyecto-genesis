package com.genesis.proyecto2.exception;

/**
 * Se lanza cuando un administrador intenta eliminar un Plan que aún tiene
 * suscripciones activas. Según lo especificado en openapi.yaml:
 * <pre>DELETE /plans/{planId} → 400 "Cannot delete plan with active subscriptions"</pre>
 * <p>Mapea al código HTTP 400 Bad Request.</p>
 *
 * <p>Ejemplo de uso:</p>
 * <pre>
 * if (suscripcionRepository.existsByPlanAndActivoTrue(plan)) {
 * throw new PlanHasActiveSubscriptionsException(plan.getNombre());
 * }
 * </pre>
 */
public class PlanHasActiveSubscriptionsException extends BusinessException {

    public PlanHasActiveSubscriptionsException(String planName) {
        super("El plan '" + planName + "' no puede eliminarse porque tiene "
                + "suscripciones activas.");
    }

    public PlanHasActiveSubscriptionsException(String message, boolean raw) {
        super(message);
    }
}
