package com.genesis.proyecto2.exception;

/**
 * Se lanza cuando un recurso solicitado de Usuario, Plan, Operación etc, no
 * existe en la base de datos.
 * <p>Mapea al código HTTP 404 Not Found.</p>
 *
 * <p>Ejemplos de uso:</p>
 * <pre>
 * throw new ResourceNotFoundException("Plan", planId);
 * throw new ResourceNotFoundException("Usuario", userId);
 * </pre>
 */
public class ResourceNotFoundException extends BusinessException {

    public ResourceNotFoundException(String resourceName, Object identifier) {
        super(resourceName + " con identificador '" + identifier + "' no fue encontrado.");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
