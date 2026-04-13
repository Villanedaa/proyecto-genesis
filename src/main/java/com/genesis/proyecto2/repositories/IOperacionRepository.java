package com.genesis.proyecto2.repositories;

import com.genesis.proyecto2.entities.Operacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para la entidad Operacion.
 * Proporciona métodos para interactuar con la tabla de operaciones en MySQL.
 */
@Repository
public interface IOperacionRepository extends JpaRepository<Operacion, Long> {

    /**
     * Busca una operación por su nombre o tipo (ej. 'SUMA', 'CALCULO_IMC').
     * Útil para validar lógica de negocio antes de ejecutar transacciones.
     */
    Optional<Operacion> findByCodigo(String codigo);
}