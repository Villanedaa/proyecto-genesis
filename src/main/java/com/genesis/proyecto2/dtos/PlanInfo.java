package com.genesis.proyecto2.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlanInfo {
    private Long id;
    private String name;
    private Integer tokensGranted;
}
