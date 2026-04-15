package com.genesis.proyecto2.config;

import com.genesis.proyecto2.entities.Operacion;
import com.genesis.proyecto2.entities.Plan;
import com.genesis.proyecto2.entities.TasaCambio;
import com.genesis.proyecto2.repositories.IOperacionRepository;
import com.genesis.proyecto2.repositories.IPlanRepository;
import com.genesis.proyecto2.repositories.ITasaCambioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final IPlanRepository planRepository;
    private final IOperacionRepository operacionRepository;
    private final ITasaCambioRepository tasaCambioRepository;

    @Override
    public void run(String... args) throws Exception {
        loadPlans();
        loadOperations();
        loadTasaCambio();
    }

    private void loadPlans() {
        if (planRepository.count() == 0) {
            Plan free = new Plan();
            free.setNombre("Free");
            free.setTokensOtorgados(200);
            free.setEstado("ACTIVO");

            Plan pro = new Plan();
            pro.setNombre("Pro");
            pro.setTokensOtorgados(1000);
            pro.setEstado("ACTIVO");

            Plan enterprise = new Plan();
            enterprise.setNombre("Enterprise");
            enterprise.setTokensOtorgados(5000);
            enterprise.setEstado("ACTIVO");

            planRepository.save(free);
            planRepository.save(pro);
            planRepository.save(enterprise);
        }
    }

    private void loadOperations() {
        if (operacionRepository.count() == 0) {
            createOperation("OP-01", "¿Cuánto me cuesta ese crédito?", 50);
            createOperation("OP-02", "Conversor COP ↔ USD", 20);
            createOperation("OP-03", "Calculadora de IMC", 15);
            createOperation("OP-04", "Calculadora de sueño", 20);
        }
    }

    private void createOperation(String code, String name, Integer baseCost) {
        Operacion op = new Operacion();
        op.setCodigo(code);
        op.setNombre(name);
        op.setCostoBase(baseCost);
        op.setEstado("ACTIVO");
        operacionRepository.save(op);
    }

    private void loadTasaCambio() {
        if (tasaCambioRepository.count() == 0) {
            TasaCambio tasa = new TasaCambio();
            tasa.setValorCopPorUsd(new BigDecimal("4000.00")); // Valor por defecto
            tasaCambioRepository.save(tasa);
        }
    }
}
