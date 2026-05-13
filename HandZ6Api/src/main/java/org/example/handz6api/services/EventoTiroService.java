// services/EventoTiroService.java
package org.example.handz6api.services;

import org.example.handz6api.model.EventoTiro;
import org.example.handz6api.model.enums.ResultadoTiro;
import org.example.handz6api.repositories.EventoTiroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoTiroService {

    private final EventoTiroRepository eventoTiroRepository;

    public EventoTiroService(EventoTiroRepository eventoTiroRepository) {
        this.eventoTiroRepository = eventoTiroRepository;
    }

    public EventoTiro guardar(EventoTiro eventoTiro) {
        return eventoTiroRepository.save(eventoTiro);
    }

    public Optional<EventoTiro> obtenerPorId(Long eventoId) {
        return eventoTiroRepository.findById(eventoId);
    }

    public List<EventoTiro> obtenerPorPartido(Long partidoId) {
        return eventoTiroRepository.findByEventoPartidoId(partidoId);
    }

    public List<EventoTiro> obtenerPorJugador(Long jugadorId) {
        return eventoTiroRepository.findByEventoJugadorId(jugadorId);
    }

    public List<EventoTiro> obtenerPorPortero(Long porteroId) {
        return eventoTiroRepository.findByPorteroId(porteroId);
    }

    public long contarPorResultadoYPartido(Long partidoId, String resultado) {
        return eventoTiroRepository.countByEventoPartidoIdAndResultado(
                partidoId,
                ResultadoTiro.valueOf(resultado)
        );
    }

    public void eliminar(Long eventoId) {
        eventoTiroRepository.deleteById(eventoId);
    }
}