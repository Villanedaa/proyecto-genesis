package com.genesis.proyecto2.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RechargeTokensRequest {
    @NotNull(message = "El monto es obligatorio")
    @Min(value = 1, message = "El monto debe ser mínimo 1")
    private Integer amount;
}
