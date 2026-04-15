package com.genesis.proyecto2.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Long id;
    private String operationCode;
    private Integer tokensInput;
    private Integer tokensOutput;
    private Integer totalCost;
    private LocalDateTime timestamp;
}
