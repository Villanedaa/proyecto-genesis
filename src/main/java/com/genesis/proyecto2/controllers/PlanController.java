package com.genesis.proyecto2.controllers;

import com.genesis.proyecto2.dtos.PaginatedPlans;
import com.genesis.proyecto2.dtos.PlanRequest;
import com.genesis.proyecto2.dtos.PlanResponse;
import com.genesis.proyecto2.entities.Plan;
import com.genesis.proyecto2.exception.PlanHasActiveSubscriptionsException;
import com.genesis.proyecto2.exception.ResourceNotFoundException;
import com.genesis.proyecto2.repositories.IPlanRepository;
import com.genesis.proyecto2.repositories.ISuscripcionRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 12/04/2026
 * */
@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController {

    private final IPlanRepository planRepository;
    private final ISuscripcionRepository suscripcionRepository;

    @GetMapping
    public ResponseEntity<PaginatedPlans> getPlans(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        
        Page<Plan> planPage = planRepository.findAll(PageRequest.of(page, size));
        List<PlanResponse> content = planPage.getContent().stream().map(p -> new PlanResponse(
                p.getId(), p.getNombre(), p.getTokensOtorgados()
        )).collect(Collectors.toList());

        return ResponseEntity.ok(PaginatedPlans.builder()
                .content(content)
                .page(planPage.getNumber())
                .size(planPage.getSize())
                .totalElements((int) planPage.getTotalElements())
                .totalPages(planPage.getTotalPages())
                .build());
    }

    @PostMapping
    public ResponseEntity<PlanResponse> createPlan(@Valid @RequestBody PlanRequest request) {
        Plan plan = new Plan();
        plan.setNombre(request.getNombre());
        plan.setTokensOtorgados(request.getTokensOtorgados());
        plan.setEstado("ACTIVO");
        plan = planRepository.save(plan);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new PlanResponse(plan.getId(), plan.getNombre(), plan.getTokensOtorgados())
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlanResponse> updatePlan(
            @PathVariable Long id,
            @Valid @RequestBody PlanRequest request) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan", id));
        plan.setNombre(request.getNombre());
        plan.setTokensOtorgados(request.getTokensOtorgados());
        plan = planRepository.save(plan);
        return ResponseEntity.ok(
                new PlanResponse(plan.getId(), plan.getNombre(), plan.getTokensOtorgados())
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long id) {
        Plan plan = planRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Plan", id));
        
        // Un plan no puede eliminarse si tiene suscripciones activas
        if (suscripcionRepository.existsActiveByPlanId(id)) {
            throw new PlanHasActiveSubscriptionsException("El plan no puede eliminarse porque tiene suscripciones activas.");
        }
        
        planRepository.delete(plan);
        return ResponseEntity.noContent().build();
    }
}
