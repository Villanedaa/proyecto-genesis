package com.genesis.proyecto2.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 12/04/2026
 * */
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRolId implements Serializable {
    private Long usuario_id; // Debe coincidir con el nombre en tu SQL
    private Long rol_id;
}