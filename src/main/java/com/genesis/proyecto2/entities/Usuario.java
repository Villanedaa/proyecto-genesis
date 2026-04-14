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
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_usuario") // Columna SQL: nombre_usuario
    private String nombreUsuario;    // Lombok genera: setNombreUsuario()

    @Column(name = "correo")
    private String correo;

    @Column(name = "contrasenia")    // Columna SQL: contrasenia
    private String contrasenia;      // Lombok genera: setContrasenia()

    @Column(name = "saldo_tokens")   // Columna SQL: saldo_tokens
    private Integer saldoTokens;     // Lombok genera: setSaldoTokens()

    @Column(name = "estado")
    private String estado;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioRol> usuarioRoles;
}