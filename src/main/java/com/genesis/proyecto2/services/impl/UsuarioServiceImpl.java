package com.genesis.proyecto2.services.impl;

import com.genesis.proyecto2.dtos.RegistroUsuario;
import com.genesis.proyecto2.dtos.UsuarioResponse;
import com.genesis.proyecto2.dtos.UsuarioRolCrearRequest;
import com.genesis.proyecto2.dtos.PlanInfo;
import com.genesis.proyecto2.dtos.UserProfileResponse;
import com.genesis.proyecto2.entities.Plan;
import com.genesis.proyecto2.entities.Suscripcion;
import com.genesis.proyecto2.entities.Transaccion;
import com.genesis.proyecto2.entities.Usuario;
import com.genesis.proyecto2.exception.DuplicateResourceException;
import com.genesis.proyecto2.exception.InvalidSubscriptionException;
import com.genesis.proyecto2.exception.ResourceNotFoundException;
import com.genesis.proyecto2.repositories.IPlanRepository;
import com.genesis.proyecto2.repositories.ISuscripcionRepository;
import com.genesis.proyecto2.repositories.ITransaccionRepository;
import com.genesis.proyecto2.repositories.IUsuarioRepository;
import com.genesis.proyecto2.services.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {

    private final IUsuarioRepository usuarioRepository;
    private final ITransaccionRepository transaccionRepository;
    private final ISuscripcionRepository suscripcionRepository;
    private final IPlanRepository planRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponse> findAll() {
        return usuarioRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioResponse> findById(Long id) {
        return Optional.of(
                usuarioRepository.findById(id)
                        .map(this::toResponse)
                        .orElseThrow(() -> new ResourceNotFoundException("Usuario", id))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioResponse> findByNombreUsuario(String nombreUsuario) {
        return usuarioRepository.findByNombreUsuario(nombreUsuario).map(this::toResponse);
    }

    @Override
    @Transactional
    public UsuarioResponse save(RegistroUsuario dto) {
        // Verificamos unicidad antes de persistir
        if (usuarioRepository.findByNombreUsuario(dto.getNombreUsuario()).isPresent()) {
            throw new DuplicateResourceException("nombreUsuario", dto.getNombreUsuario());
        }
        if (usuarioRepository.findByCorreo(dto.getCorreo()).isPresent()) {
            throw new DuplicateResourceException("correo", dto.getCorreo());
        }

        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(dto.getNombreUsuario());
        usuario.setCorreo(dto.getCorreo());

        // Encriptar antes de guardar
        usuario.setContrasenia(passwordEncoder.encode(dto.getContrasenia()));

        usuario.setSaldoTokens(100);
        usuario.setEstado("ACTIVO");

        // No seteamos fechaCreacion porque JPA tiene insertable = false
        return toResponse(usuarioRepository.save(usuario));
    }

    @Override
    @Transactional
    public UsuarioResponse asignarRol(UsuarioRolCrearRequest request) {
        // Lanzamos ResourceNotFoundException si el usuario no existe
        return findById(request.getUsuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", request.getUsuarioId()));
    }

    @Override
    @Transactional
    public Optional<UsuarioResponse> update(Long id, RegistroUsuario dto) {
        return usuarioRepository.findById(id).map(existing -> {
            existing.setNombreUsuario(dto.getNombreUsuario());
            existing.setCorreo(dto.getCorreo());

            // Si la contraseña viene en el DTO, se encripta
            if (dto.getContrasenia() != null && !dto.getContrasenia().isEmpty()) {
                existing.setContrasenia(passwordEncoder.encode(dto.getContrasenia()));
            }

            return toResponse(usuarioRepository.save(existing));
        });
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!usuarioRepository.existsById(id)) return false;
        usuarioRepository.deleteById(id);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfileResponse getProfile(String email) {
        Usuario usuario = usuarioRepository.findByCorreo(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", email));

        UserProfileResponse response = new UserProfileResponse();
        response.setId(usuario.getId());
        response.setName(usuario.getNombreUsuario());
        response.setEmail(usuario.getCorreo());
        response.setBalance(usuario.getSaldoTokens());
        response.setStatus(usuario.getEstado());
        
        // Cargar plan activo
        suscripcionRepository.findActiveByUsuarioId(usuario.getId())
                .ifPresent(sub -> {
                    PlanInfo planInfo = new PlanInfo();
                    planInfo.setId(sub.getPlan().getId());
                    planInfo.setName(sub.getPlan().getNombre());
                    planInfo.setTokensGranted(sub.getPlan().getTokensOtorgados());
                    response.setActivePlan(planInfo);
                });
        
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public com.genesis.proyecto2.dtos.PaginatedTransactions getTransactions(String email, int page, int size) {
        Usuario usuario = usuarioRepository.findByCorreo(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", email));

        Page<Transaccion> pageRes = transaccionRepository.findByUsuarioId(usuario.getId(), PageRequest.of(page, size));
        
        List<com.genesis.proyecto2.dtos.TransactionDto> content = pageRes.getContent().stream().map(t -> {
            com.genesis.proyecto2.dtos.TransactionDto dto = new com.genesis.proyecto2.dtos.TransactionDto();
            dto.setId(t.getId());
            dto.setOperationCode(t.getOperacion().getCodigo());
            dto.setTokensInput(t.getTokensEntrada());
            dto.setTokensOutput(t.getTokensSalida());
            dto.setTotalCost(t.getCostoTotal());
            dto.setTimestamp(t.getFecha());
            return dto;
        }).collect(Collectors.toList());

        return com.genesis.proyecto2.dtos.PaginatedTransactions.builder()
                .content(content)
                .page(pageRes.getNumber())
                .size(pageRes.getSize())
                .totalElements((int) pageRes.getTotalElements())
                .totalPages(pageRes.getTotalPages())
                .build();
    }

    @Override
    @Transactional
    public void subscribeToPlan(String email, com.genesis.proyecto2.dtos.SubscribeRequest request) {
        Usuario usuario = usuarioRepository.findByCorreo(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", email));

        Plan nuevoPlan = planRepository.findById(request.getPlanId())
                .orElseThrow(() -> new ResourceNotFoundException("Plan", request.getPlanId()));

        if (!"ACTIVO".equals(nuevoPlan.getEstado())) {
            throw new InvalidSubscriptionException("El plan seleccionado no está activo.");
        }

        // Desactivar plan anterior si existe
        suscripcionRepository.findActiveByUsuarioId(usuario.getId()).ifPresent(sub -> {
            sub.setEstado("INACTIVO");
            sub.setFechaFin(LocalDateTime.now());
            suscripcionRepository.save(sub);
        });

        // Crear nueva suscripción
        Suscripcion nuevaSuscripcion = new Suscripcion();
        nuevaSuscripcion.setUsuario(usuario);
        nuevaSuscripcion.setPlan(nuevoPlan);
        nuevaSuscripcion.setTokensAsignados(nuevoPlan.getTokensOtorgados());
        nuevaSuscripcion.setFechaInicio(LocalDateTime.now());
        nuevaSuscripcion.setEstado("ACTIVO");
        suscripcionRepository.save(nuevaSuscripcion);

        // Acreditar tokens al usuario (sobreescribimos o sumamos? Genesis dice: "el equipo debe decidir... qué sucede con el saldo anterior". Sumemos el saldo anterior con el nuevo del plan).
        usuario.setSaldoTokens(usuario.getSaldoTokens() + nuevoPlan.getTokensOtorgados());
        usuarioRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public com.genesis.proyecto2.dtos.PaginatedUsers getAdminUsers(int page, int size) {
        Page<Usuario> pageRes = usuarioRepository.findAll(PageRequest.of(page, size));
        
        List<com.genesis.proyecto2.dtos.UserAdminView> content = pageRes.getContent().stream().map(u -> {
            com.genesis.proyecto2.dtos.UserAdminView view = new com.genesis.proyecto2.dtos.UserAdminView();
            view.setId(u.getId());
            view.setEmail(u.getCorreo());
            view.setBalance(u.getSaldoTokens());
            view.setStatus(u.getEstado());
            suscripcionRepository.findActiveByUsuarioId(u.getId())
                .ifPresentOrElse(
                    sub -> view.setActivePlan(sub.getPlan().getNombre()),
                    () -> view.setActivePlan("None")
                );
            return view;
        }).collect(Collectors.toList());

        return com.genesis.proyecto2.dtos.PaginatedUsers.builder()
                .content(content)
                .page(pageRes.getNumber())
                .size(pageRes.getSize())
                .totalElements((int) pageRes.getTotalElements())
                .totalPages(pageRes.getTotalPages())
                .build();
    }

    @Override
    @Transactional
    public void updateUserStatus(Long userId, String status) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", userId));
        usuario.setEstado(status);
        usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void rechargeTokens(Long userId, Integer amount) {
        Usuario usuario = usuarioRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", userId));
        usuario.setSaldoTokens(usuario.getSaldoTokens() + amount);
        usuarioRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public com.genesis.proyecto2.dtos.MetricsResponse getMetrics() {
        com.genesis.proyecto2.dtos.MetricsResponse metrics = new com.genesis.proyecto2.dtos.MetricsResponse();
        
        metrics.setTokensConsumedPerDay(
            transaccionRepository.getTokensConsumedPerDay().stream()
                .map(obj -> new com.genesis.proyecto2.dtos.MetricsResponse.TokenConsumptionByDay(
                    obj[0].toString(), 
                    ((Number) obj[1]).intValue()))
                .collect(Collectors.toList())
        );

        metrics.setMostExecutedOperations(
            transaccionRepository.getMostExecutedOperations().stream()
                .map(obj -> new com.genesis.proyecto2.dtos.MetricsResponse.OperationExecutionCount(
                    obj[0].toString(), 
                    ((Number) obj[1]).intValue()))
                .collect(Collectors.toList())
        );

        metrics.setHighestConsumingUsers(
            transaccionRepository.getHighestConsumingUsers().stream()
                .map(obj -> new com.genesis.proyecto2.dtos.MetricsResponse.UserConsumption(
                    ((Number) obj[0]).longValue(), 
                    obj[1].toString(), 
                    ((Number) obj[2]).intValue()))
                .collect(Collectors.toList())
        );

        return metrics;
    }

    private UsuarioResponse toResponse(Usuario u) {
        UsuarioResponse res = new UsuarioResponse();
        res.setId(u.getId());
        res.setNombreUsuario(u.getNombreUsuario());
        res.setCorreo(u.getCorreo());
        res.setSaldoTokens(u.getSaldoTokens());
        res.setEstado(u.getEstado());
        res.setFechaCreacion(u.getFechaCreacion());
        return res;
    }
}