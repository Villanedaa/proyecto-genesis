package com.genesis.proyecto2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "operaciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "transacciones")
public class Operacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "codigo", nullable = false, unique = true, length = 10)
    private String codigo;
    @Column(name = "nombre", length = 100)
    private String nombre;
    @Column(name = "costo_base", nullable = false)
    private Integer costoBase;
    @Column(name = "estado", nullable = false, length = 20)
    private String estado;
    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private LocalDateTime fechaCreacion;
    // @OneToMany(mappedBy = "operacion")
    // private List<Transaccion> transacciones;

}
