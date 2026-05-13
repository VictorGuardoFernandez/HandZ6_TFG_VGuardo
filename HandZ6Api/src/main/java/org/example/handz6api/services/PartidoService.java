package org.example.handz6api.services;

import org.example.handz6api.model.Partido;
import org.example.handz6api.repositories.PartidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartidoService {

    private final PartidoRepository partidoRepository;

    public PartidoService(PartidoRepository partidoRepository) {
        this.partidoRepository = partidoRepository;
    }

    // 📌 Crear o actualizar partido
    public Partido guardarPartido(Partido partido) {
        return partidoRepository.save(partido);
    }

    // 📌 Obtener todos los partidos
    public List<Partido> obtenerTodos() {
        return partidoRepository.findAll();
    }

    // 📌 Buscar por ID
    public Optional<Partido> obtenerPorId(Long id) {
        return partidoRepository.findById(id);
    }

    // 📌 Eliminar partido
    public void eliminar(Long id) {
        partidoRepository.deleteById(id);
    }

    // 📌 Actualizar marcador (útil para tu EventoService)
    public Partido actualizarMarcador(Long id, Integer golesLocal, Integer golesVisitante) {
        Partido partido = partidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado"));

        partido.setGolesLocal(golesLocal);
        partido.setGolesVisitante(golesVisitante);

        return partidoRepository.save(partido);
    }

    // 📌 Cambiar estado del partido (ej: EN_JUEGO, FINALIZADO)
    public Partido actualizarEstado(Long id, String estado) {
        Partido partido = partidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partido no encontrado"));

        partido.setEstado(estado);
        return partidoRepository.save(partido);
    }
}