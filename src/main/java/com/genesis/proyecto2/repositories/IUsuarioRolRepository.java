package com.genesis.proyecto2.repositories;

import com.genesis.proyecto2.entities.UsuarioRol;
import com.genesis.proyecto2.entities.UsuarioRolId; // La llave compuesta
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 13/04/2026
 * */
@Repository
public interface IUsuarioRolRepository extends JpaRepository<UsuarioRol, UsuarioRolId> {


    @Query("SELECT ur FROM UsuarioRol ur WHERE ur.id.usuario_id = :usuarioId")
    List<UsuarioRol> buscarPorUsuarioId(@Param("usuarioId") Long usuarioId);

    @Query("SELECT ur FROM UsuarioRol ur WHERE ur.id.rol_id = :rolId")
    List<UsuarioRol> buscarPorRolId(@Param("rolId") Long rolId);
}