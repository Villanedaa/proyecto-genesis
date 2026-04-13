package com.genesis.proyecto2.exception;

/**
 * Clase base para todas las excepciones de dominio/negocio en Genesis.
 * <p>
 * Las subclases deben portar el estado HTTP que desean que el manejador devuelva,
 * y un mensaje {@code message} legible. El uso de una jerarquía mantiene el
 * {@link com.genesis.proyecto2.exception.handler.GlobalExceptionHandler}
 * limpio (un capturador general para excepciones de negocio + un capturador específico por
 * tipo de excepción cuando se necesiten diferentes códigos de estado HTTP).
 * </p>
 */
public abstract class BusinessException extends RuntimeException {

    protected BusinessException(String message) {
        super(message);
    }

    protected BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
