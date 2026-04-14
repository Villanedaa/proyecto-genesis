package com.genesis.proyecto2.services;

import com.genesis.proyecto2.dtos.OperacionResponse;
import java.util.List;

/**
 * Interfaz para la gestión del catálogo de operaciones.
 * Permite listar las acciones disponibles y validar costos.
 */
public interface IOperacionService {

    /**
     * Recupera todas las operaciones que están activas en el sistema.
     * @return Lista de DTOs con la información de cada operación.
     */
    List<OperacionResponse> findAllActive();

    /**
     * Busca una operación específica por su ID.
     * Útil para validar el costo antes de una transacción.
     * @param id Identificador de la operación.
     * @return DTO de la operación encontrada.
     */
    OperacionResponse findById(Long id);

    /**
     * Método para verificar si una operación existe y está habilitada.
     * @param id Identificador de la operación.
     * @return true si está disponible, false de lo contrario.
     */
    boolean existsById(Long id);
}