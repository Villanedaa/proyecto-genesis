package com.genesis.proyecto2.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MetricsResponse {
    private List<TokenConsumptionByDay> tokensConsumedPerDay;
    private List<OperationExecutionCount> mostExecutedOperations;
    private List<UserConsumption> highestConsumingUsers;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TokenConsumptionByDay {
        private String date;
        private Integer tokens;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OperationExecutionCount {
        private String operationCode;
        private Integer executionCount;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserConsumption {
        private Long userId;
        private String userEmail;
        private Integer totalTokensConsumed;
    }
}
