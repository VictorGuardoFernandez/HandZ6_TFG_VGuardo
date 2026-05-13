// services/RolService.java
package org.example.handz6api.services;

import org.example.handz6api.model.Rol;
import org.example.handz6api.repositories.RolRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {

    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public Rol guardar(Rol rol) {
        return rolRepository.save(rol);
    }

    public List<Rol> obtenerTodos() {
        return rolRepository.findAll();
    }

    public Optional<Rol> obtenerPorId(Long id) {
        return rolRepository.findById(id);
    }

    public void eliminar(Long id) {
        rolRepository.deleteById(id);
    }
}