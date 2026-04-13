package com.genesis.proyecto2.exception.handler;

import com.genesis.proyecto2.exception.DuplicateResourceException;
import com.genesis.proyecto2.exception.ErrorResponse;
import com.genesis.proyecto2.exception.InsufficientTokensException;
import com.genesis.proyecto2.exception.InvalidSubscriptionException;
import com.genesis.proyecto2.exception.OperationInactiveException;
import com.genesis.proyecto2.exception.PlanHasActiveSubscriptionsException;
import com.genesis.proyecto2.exception.ResourceNotFoundException;
import com.genesis.proyecto2.exception.UserInactiveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * Manejador centralizado de excepciones para la API de Genesis.
 *
 * <p>OJO! Pendiente de mapear cada tipo de excepción a la estructura {@link ErrorResponse} definida
 * en {@code openapi.yaml} y al código de estado HTTP correcto:</p>
 *
 * <table border="1">
 * <tr><th>Excepción</th><th>Estado HTTP</th></tr>
 * <tr><td>MethodArgumentNotValidException (Bean Validation)</td><td>400</td></tr>
 * <tr><td>InsufficientTokensException</td><td>400</td></tr>
 * <tr><td>PlanHasActiveSubscriptionsException</td><td>400</td></tr>
 * <tr><td>OperationInactiveException</td><td>400</td></tr>
 * <tr><td>InvalidSubscriptionException</td><td>400</td></tr>
 * <tr><td>DuplicateResourceException</td><td>409</td></tr>
 * <tr><td>ResourceNotFoundException</td><td>404</td></tr>
 * <tr><td>UserInactiveException</td><td>403</td></tr>
 * <tr><td>BadCredentialsException</td><td>401</td></tr>
 * <tr><td>AccessDeniedException</td><td>403</td></tr>
 * <tr><td>Cualquier otra RuntimeException / Exception</td><td>500</td></tr>
 * </table>
 *
 * <p>Principio seguido: <b>Responsabilidad Única</b> — esta clase solo
 * convierte excepciones en respuestas HTTP; la lógica de negocio permanece en los servicios.</p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // ── Validación de Bean (javax/jakarta @Valid) ────────────────────────────────

    /**
     * Maneja errores de validación provenientes de {@code @Valid} en los cuerpos de las peticiones.
     * Produce un arreglo de detalles {@code details} campo por campo que coincide con el
     * esquema {@code ErrorResponse} en openapi.yaml.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(
            MethodArgumentNotValidException ex) {

        List<ErrorResponse.FieldError> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> new ErrorResponse.FieldError(
                        fe.getField(),
                        fe.getDefaultMessage()))
                .toList();

        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.of("Error de validación en los campos.", details));
    }

    // ── 400 Bad Request — reglas de negocio ─────────────────────────────────────

    @ExceptionHandler(InsufficientTokensException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientTokens(
            InsufficientTokensException ex) {
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.of(ex.getMessage(), "saldo", ex.getMessage()));
    }

    @ExceptionHandler(PlanHasActiveSubscriptionsException.class)
    public ResponseEntity<ErrorResponse> handlePlanHasSubscriptions(
            PlanHasActiveSubscriptionsException ex) {
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(OperationInactiveException.class)
    public ResponseEntity<ErrorResponse> handleOperationInactive(
            OperationInactiveException ex) {
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(InvalidSubscriptionException.class)
    public ResponseEntity<ErrorResponse> handleInvalidSubscription(
            InvalidSubscriptionException ex) {
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.of(ex.getMessage()));
    }

    // ── 404 Not Found ─────────────────────────────────────────────────────────

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(
            ResourceNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(ex.getMessage()));
    }

    // ── 409 Conflict ─────────────────────────────────────────────────────────

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(
            DuplicateResourceException ex) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ErrorResponse.of(ex.getMessage()));
    }

    // ── 401 Unauthorized ─────────────────────────────────────────────────────

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentials(
            BadCredentialsException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ErrorResponse.of("Credenciales inválidas."));
    }

    // ── 403 Forbidden ────────────────────────────────────────────────────────

    @ExceptionHandler(UserInactiveException.class)
    public ResponseEntity<ErrorResponse> handleUserInactive(
            UserInactiveException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ErrorResponse.of(ex.getMessage()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDenied(
            AccessDeniedException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ErrorResponse.of("No tienes permisos para realizar esta acción."));
    }

    // ── 500 Internal Server Error (red de seguridad) ───────────────────────────────

    /**
     * Manejador de red de seguridad. En producción, esto evita que los rastreos de pila (stack traces)
     * se filtren al cliente, mientras sigue devolviendo una estructura {@code ErrorResponse} organizada.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(
                        "Ocurrió un error interno. Por favor intente más tarde."));
    }
}