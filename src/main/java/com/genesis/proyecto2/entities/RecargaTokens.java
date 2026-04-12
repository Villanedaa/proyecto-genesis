package com.genesis.proyecto2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "recarga_tokens")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecargaTokens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    // Aqui tenemos presente que yo nombre "usuarioReceptor" con el fin de entender que esta clase procesa y toma los datos de quien recibe y quien envia tokens
    // Relación 1: El usuario que recibe el saldo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuarioReceptor;
    // Relación 2: El administrador que autoriza (también es un Usuario)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private Usuario administrador;
    @Column(name = "monto")
    private Integer monto;
    @Column(name = "saldo_antes")
    private Integer saldoAntes;
    @Column(name = "saldo_despues")
    private Integer saldoDespues;
    @Column(name = "observacion")
    private String observacion;
    @Column(name = "fecha", insertable = false, nullable = false)
    private LocalDateTime fecha;
}
