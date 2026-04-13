package com.genesis.proyecto2.repositories;

import com.genesis.proyecto2.entities.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPlanRepository extends JpaRepository<Plan, Long> {
    Optional<Plan> findByNombre(String nombre);
}
