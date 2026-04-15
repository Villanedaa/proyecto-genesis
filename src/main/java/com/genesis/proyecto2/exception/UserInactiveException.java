package com.genesis.proyecto2.exception;

/**
 * Se lanza cuando un usuario intenta realizar una acción que viola una regla
 * de negocio relacionada con el estado de su cuenta eje la cuenta está INACTIVA.
 * <p>Mapea al código HTTP 403 Forbidden.</p>
 *
 * <p>Ejemplo de uso:</p>
 * <pre>
 * if (!usuario.isActivo()) {
 * throw new UserInactiveException(usuario.getEmail());
 * }
 * </pre>
 */
public class UserInactiveException extends BusinessException {

    public UserInactiveException(String email) {
        super("La cuenta asociada a '" + email + "' está desactivada. "
                + "Contacte al administrador.");
    }

    public UserInactiveException(String message, boolean raw) {
        super(message);
    }
}
