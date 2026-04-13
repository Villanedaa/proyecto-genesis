package com.genesis.proyecto2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "transaccion")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"usuario", "operacion"})
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operacion_id", nullable = false)
    private Operacion operacion;
    @Column(name = "tokens_entrada")
    private Integer tokensEntrada;
    @Column(name = "tokens_salida")
    private Integer tokensSalida;
    @Column(name = "costo_total")
    private Integer costoTotal;
    @Column(name = "fecha", insertable = false, updatable = false)
    private LocalDateTime fecha;
}
