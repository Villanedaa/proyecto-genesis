package com.genesis.proyecto2.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.genesis.proyecto2.dtos.*;
import com.genesis.proyecto2.entities.Operacion;
import com.genesis.proyecto2.entities.Transaccion;
import com.genesis.proyecto2.entities.Usuario;
import com.genesis.proyecto2.exception.InsufficientTokensException;
import com.genesis.proyecto2.exception.OperationInactiveException;
import com.genesis.proyecto2.exception.ResourceNotFoundException;
import com.genesis.proyecto2.repositories.IOperacionRepository;
import com.genesis.proyecto2.repositories.ITransaccionRepository;
import com.genesis.proyecto2.repositories.IUsuarioRepository;
import com.genesis.proyecto2.services.ICalculadoraService;
import com.genesis.proyecto2.services.IExecutionFacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ExecutionFacadeServiceImpl implements IExecutionFacadeService {

    private final ICalculadoraService calculadoraService;
    private final IUsuarioRepository usuarioRepository;
    private final IOperacionRepository operacionRepository;
    private final ITransaccionRepository transaccionRepository;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public CreditoResponse executeCreditCalculator(String email, CreditoRequest request) {
        return execute(email, "OP-01", request, () -> calculadoraService.calcularCredito(request));
    }

    @Override
    @Transactional
    public ConversorResponse executeCurrencyConverter(String email, ConversorRequest request) {
        return execute(email, "OP-02", request, () -> calculadoraService.convertirMoneda(request));
    }

    @Override
    @Transactional
    public ImcReponse executeBmiCalculator(String email, ImcRequest request) {
        return execute(email, "OP-03", request, () -> calculadoraService.calcularImc(request));
    }

    @Override
    @Transactional
    public CalculadoraSuenioResponse executeSleepCalculator(String email, CalculadoraSuenioRequest request) {
        return execute(email, "OP-04", request, () -> calculadoraService.calcularSuenio(request));
    }

    private <REQ, RES> RES execute(String email, String operationCode, REQ request, CalculationAction<RES> action) {
        Usuario usuario = usuarioRepository.findByCorreo(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", email));

        Operacion operacion = operacionRepository.findByCodigo(operationCode)
                .orElseThrow(() -> new ResourceNotFoundException("Operacion", operationCode));

        if (!"ACTIVO".equals(operacion.getEstado())) {
            throw new OperationInactiveException(operacion.getNombre());
        }

        int tokensEntrada;
        try {
            String jsonRequest = objectMapper.writeValueAsString(request);
            tokensEntrada = (int) Math.floor(jsonRequest.length() / 4.0);
        } catch (Exception e) {
            tokensEntrada = 0;
        }

        int costoBase = operacion.getCostoBase();
        
        // Primera validación: si no tiene para el mínimo, rechazar inmediatamente
        if (usuario.getSaldoTokens() < (costoBase + tokensEntrada)) {
            throw new InsufficientTokensException(costoBase + tokensEntrada, usuario.getSaldoTokens());
        }

        // Ejecutar el cálculo matemático y obtener salida real
        RES response = action.calculate();

        int tokensSalida;
        try {
            String jsonResponse = objectMapper.writeValueAsString(response);
            tokensSalida = (int) Math.floor(jsonResponse.length() / 4.0);
        } catch (Exception e) {
            tokensSalida = 0;
        }

        int costoTotal = costoBase + tokensEntrada + tokensSalida;

        // Segunda validación: conociendo costo total final, debita.
        if (usuario.getSaldoTokens() < costoTotal) {
            throw new InsufficientTokensException(costoTotal, usuario.getSaldoTokens());
        }

        // Debitar y guardar auditoría
        usuario.setSaldoTokens(usuario.getSaldoTokens() - costoTotal);
        usuarioRepository.save(usuario);

        Transaccion transaccion = new Transaccion();
        transaccion.setUsuario(usuario);
        transaccion.setOperacion(operacion);
        transaccion.setTokensEntrada(tokensEntrada);
        transaccion.setTokensSalida(tokensSalida);
        transaccion.setCostoTotal(costoTotal);
        transaccion.setFecha(LocalDateTime.now());
        transaccionRepository.save(transaccion);

        return response;
    }

    @FunctionalInterface
    private interface CalculationAction<T> {
        T calculate();
    }
}
