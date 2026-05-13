package org.example.handz6api.services;

import org.example.handz6api.model.Equipo;
import org.example.handz6api.repositories.EquipoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {

    private final EquipoRepository equipoRepository;
    private final FileStorageService fileStorageService;

    public EquipoService(EquipoRepository equipoRepository, FileStorageService fileStorageService) {
        this.equipoRepository = equipoRepository;
        this.fileStorageService = fileStorageService;
    }

    // 📌 Crear o actualizar equipo
    public Equipo guardar(Equipo equipo) {
        return equipoRepository.save(equipo);
    }
    public Equipo subirLogo(Long equipoId, MultipartFile file) throws IOException {
        Equipo equipo = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        // Elimina el logo anterior si existe
        fileStorageService.eliminarLogo(equipo.getLogoUrl());

        // Guarda el nuevo logo y actualiza la URL
        String logoUrl = fileStorageService.guardarLogo(file);
        equipo.setLogoUrl(logoUrl);

        return equipoRepository.save(equipo);
    }

    // 📌 Obtener todos los equipos
    public List<Equipo> obtenerTodos() {
        return equipoRepository.findAll();
    }

    // 📌 Buscar por ID
    public Optional<Equipo> obtenerPorId(Long id) {
        return equipoRepository.findById(id);
    }

    // 📌 Eliminar equipo
    public void eliminar(Long id) {
        equipoRepository.deleteById(id);
    }

}