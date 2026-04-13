package com.genesis.proyecto2.services;

import com.genesis.proyecto2.entities.UsuarioRol;
import java.util.List;

/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * Servicio especializado en la relación entre Usuarios y Roles.
 * Maneja la lógica de persistencia de la tabla intermedia.
 */
public interface IUsuarioRolService {
    List<UsuarioRol> findByUsuarioId(Long usuarioId);

    List<UsuarioRol> findByRolId(Long rolId);

    UsuarioRol save(UsuarioRol usuarioRol);

    // Método para remover un rol específico de un usuario
    boolean delete(Long usuarioId, Long rolId);
}