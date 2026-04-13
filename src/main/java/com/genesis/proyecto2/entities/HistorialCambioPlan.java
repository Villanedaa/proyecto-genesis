package com.genesis.proyecto2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "historial_cambio_plan")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"usuario", "planAnterior", "planNuevo"})
public class HistorialCambioPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_anterior_id")
    private Plan planAnterior;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_nuevo_id", nullable = false)
    private Plan planNuevo;
    @Column(name = "saldo_antes", nullable = false)
    private Integer saldoAntes;
    @Column(name = "saldo_despues", nullable = false)
    private Integer saldoDespues;
    @Column(name = "fecha", insertable = false, updatable = false)
    private LocalDateTime fecha;
}