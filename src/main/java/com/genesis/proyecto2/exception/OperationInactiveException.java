package com.genesis.proyecto2.exception;

/**
 * Se lanzada cuando un usuario intenta ejecutar una operación que
 * está INACTIVA en el catálogo.
 * <p>Mapea al código HTTP 400 Bad Request.</p>
 *
 * <p>Ejemplo de uso:</p>
 * <pre>
 * if (!operacion.isActiva()) {
 * throw new OperationInactiveException(operacion.getCodigo());
 * }
 * </pre>
 */
public class OperationInactiveException extends BusinessException {

    public OperationInactiveException(String operationCode) {
        super("La operación '" + operationCode + "' está desactivada y no puede ejecutarse.");
    }

    public OperationInactiveException(String message, boolean raw) {
        super(message);
    }
}
