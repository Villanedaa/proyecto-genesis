package com.genesis.proyecto2.repositories;

import com.genesis.proyecto2.entities.Transaccion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITransaccionRepository extends JpaRepository<Transaccion, Long> {
    Page<Transaccion> findByUsuarioId(Long usuarioId, Pageable pageable);

    @Query("SELECT FUNCTION('DATE', t.fecha) as date, SUM(t.costoTotal) as tokens FROM Transaccion t GROUP BY FUNCTION('DATE', t.fecha) ORDER BY date DESC")
    List<Object[]> getTokensConsumedPerDay();

    @Query("SELECT t.operacion.codigo as code, COUNT(t) as count FROM Transaccion t GROUP BY t.operacion.codigo ORDER BY count DESC")
    List<Object[]> getMostExecutedOperations();

    @Query("SELECT u.id as id, u.correo as email, SUM(t.costoTotal) as total FROM Transaccion t JOIN t.usuario u GROUP BY u.id, u.correo ORDER BY total DESC")
    List<Object[]> getHighestConsumingUsers();
}
