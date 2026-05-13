package org.example.handz6api.services;

import org.example.handz6api.model.TipoEvento;
import org.example.handz6api.repositories.TipoEventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoEventoService {

    private final TipoEventoRepository tipoEventoRepository;

    public TipoEventoService(TipoEventoRepository tipoEventoRepository) {
        this.tipoEventoRepository = tipoEventoRepository;
    }

    // 📌 Guardar o actualizar
    public TipoEvento guardar(TipoEvento tipoEvento) {
        return tipoEventoRepository.save(tipoEvento);
    }

    // 📌 Listar todos
    public List<TipoEvento> obtenerTodos() {
        return tipoEventoRepository.findAll();
    }

    // 📌 Buscar por ID
    public Optional<TipoEvento> obtenerPorId(Long id) {
        return tipoEventoRepository.findById(id);
    }

    // 📌 Eliminar
    public void eliminar(Long id) {
        tipoEventoRepository.deleteById(id);
    }

    // 📌 Buscar por nombre (útil para tu EventoService)
    public Optional<TipoEvento> buscarPorNombre(String nombre) {
        return tipoEventoRepository.findByNombreIgnoreCase(nombre);
    }
}