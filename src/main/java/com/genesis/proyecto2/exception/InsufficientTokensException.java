package com.genesis.proyecto2.exception;

/**
 * Se lanza cuando un usuario autenticado no tiene suficiente saldo de tokens para
 * ejecutar la operación solicitada.
 * <p>Mapea al código HTTP 400 Bad Request.</p>
 *
 * <p>Ejemplo de uso:</p>
 * <pre>
 * if (usuario.getSaldo() < costoTotal) {
 * throw new InsufficientTokensException(usuario.getSaldo(), costoTotal);
 * }
 * </pre>
 */
public class InsufficientTokensException extends BusinessException {

    public InsufficientTokensException(int currentBalance, int requiredTokens) {
        super("Saldo insuficiente. Saldo actual: " + currentBalance
                + " tokens. Se requieren: " + requiredTokens + " tokens.");
    }

    public InsufficientTokensException(String message) {
        super(message);
    }
}
