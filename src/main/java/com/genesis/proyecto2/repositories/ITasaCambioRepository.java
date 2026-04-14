package com.genesis.proyecto2.repositories;

import com.genesis.proyecto2.entities.TasaCambio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 12/04/2026
 * Repositorio para la entidad TasaCambio.
 * Solo debe existir UN registro vigente pues se obtiene el más reciente
 */
@Repository
public interface ITasaCambioRepository extends JpaRepository<TasaCambio, Long> {

    /**
     * Devuelve la tasa de cambio más reciente según fecha_actualizacion
     * Es la que usa OP-02 para sus cálculos
     */
    @Query("SELECT t FROM TasaCambio t ORDER BY t.fechaActualizacion DESC LIMIT 1")
    Optional<TasaCambio> findLatest();
}
