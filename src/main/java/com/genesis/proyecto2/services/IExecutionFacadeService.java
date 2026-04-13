package com.genesis.proyecto2.services;

import com.genesis.proyecto2.dtos.*;

public interface IExecutionFacadeService {
    CreditoResponse executeCreditCalculator(String email, CreditoRequest request);
    ConversorResponse executeCurrencyConverter(String email, ConversorRequest request);
    ImcReponse executeBmiCalculator(String email, ImcRequest request);
    CalculadoraSuenioResponse executeSleepCalculator(String email, CalculadoraSuenioRequest request);
}
