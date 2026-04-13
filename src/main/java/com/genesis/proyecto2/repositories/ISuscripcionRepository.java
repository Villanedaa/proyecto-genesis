package com.genesis.proyecto2.repositories;

import com.genesis.proyecto2.entities.Suscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISuscripcionRepository extends JpaRepository<Suscripcion, Long> {
    
    @Query("SELECT s FROM Suscripcion s LEFT JOIN FETCH s.plan WHERE s.usuario.id = :usuarioId AND s.estado = 'ACTIVO'")
    Optional<Suscripcion> findActiveByUsuarioId(@Param("usuarioId") Long usuarioId);
    
    @Query("SELECT count(s) > 0 FROM Suscripcion s WHERE s.plan.id = :planId AND s.estado = 'ACTIVO'")
    boolean existsActiveByPlanId(@Param("planId") Long planId);
}
