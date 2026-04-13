package com.genesis.proyecto2.services.impl;

import com.genesis.proyecto2.entities.Rol;
import com.genesis.proyecto2.repositories.IRolRepository;
import com.genesis.proyecto2.services.IRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
/**
 * @author Sergio <sergio.grajalesc@autonoma.edu.co>
 * @author Jhojan <jhojana.villadav@autonoma.edu.co>
 * @author Sebastian <sebastian.villanedag@autonoma.edu.co>
 * @version 1.0
 * @since 13/04/2026

 */
@Service
@RequiredArgsConstructor
public class RolServiceImpl implements IRolService {

    private final IRolRepository rolRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Rol> findAll() {
        return rolRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Rol> findById(Long id) {
        return rolRepository.findById(id);
    }

    @Override
    @Transactional
    public Rol save(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    @Transactional
    public Optional<Rol> update(Long id, Rol rol) {
        return rolRepository.findById(id).map(existing -> {
            existing.setNombre(rol.getNombre());
            return rolRepository.save(existing);
        });
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        if (!rolRepository.existsById(id)) return false;
        rolRepository.deleteById(id);
        return true;
    }
}