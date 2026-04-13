package com.genesis.proyecto2.exception;

/**
 * Se lanza cuando un usuario intenta registrarse con un correo electrónico que ya está en
 * uso, o cuando se viola cualquier otra restricción de unicidad en la capa de negocio
 * (antes de que la base de datos lance un error de clave duplicada)
 * <p>Mapea al código HTTP 409 Conflict.</p>
 *
 * <p>Ejemplo de uso:</p>
 * <pre>
 * if (usuarioRepository.existsByEmail(request.email())) {
 * throw new DuplicateResourceException("Email", request.email());
 * }
 * </pre>
 */
public class DuplicateResourceException extends BusinessException {

    public DuplicateResourceException(String fieldName, Object value) {
        super("Ya existe un registro con " + fieldName + " = '" + value + "'.");
    }

    public DuplicateResourceException(String message) {
        super(message);
    }
}
