package com.genesis.proyecto2.entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRolId implements Serializable {
    private Long usuario_id; // Debe coincidir con el nombre en tu SQL
    private Long rol_id;
}