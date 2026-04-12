package com.genesis.proyecto2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "usuarioRoles")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre_usuario", nullable = false, unique = true)
    private String nombre_usuario;
    @Column(name = "contrasenia", nullable = false)
    private String contrasenia;
    @Column(name = "saldo_tokens")
    private Integer saldo_tokens;
    @Column(name = "estado")
    private String estado;
    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private LocalDateTime fecha_creacion;
    @OneToMany(mappedBy = "usuario")
    private List<UsuarioRol> usuarioRoles;
}