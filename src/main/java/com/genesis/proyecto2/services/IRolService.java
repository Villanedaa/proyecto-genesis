package com.genesis.proyecto2.services;

import com.genesis.proyecto2.entities.Rol;
import java.util.List;
import java.util.Optional;

/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * Definición de servicios para la gestión de roles de usuario
 */
public interface IRolService {
    List<Rol> findAll();
    Optional<Rol> findById(Long id);
    Rol save(Rol rol);
    Optional<Rol> update(Long id, Rol rol);
    boolean delete(Long id);
}