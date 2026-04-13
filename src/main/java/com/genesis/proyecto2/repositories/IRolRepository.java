package com.genesis.proyecto2.repositories;

import com.genesis.proyecto2.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 13/04/2026
 * */
public interface IRolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombre(String nombre);
}