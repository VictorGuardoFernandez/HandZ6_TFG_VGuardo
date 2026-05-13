package org.example.handz6api.services;

import org.example.handz6api.model.Evento;
import org.example.handz6api.model.Partido;
import org.example.handz6api.repositories.EventoRepository;
import org.example.handz6api.repositories.PartidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final PartidoRepository partidoRepository;

    public EventoService(EventoRepository eventoRepository,
                         PartidoRepository partidoRepository) {
        this.eventoRepository = eventoRepository;
        this.partidoRepository = partidoRepository;
    }

    public Evento registrarEvento(Evento evento) {

        Evento nuevo = eventoRepository.save(evento);

        // 🔥 LÓGICA CLAVE: actualizar marcador si es gol
        if (evento.getTipoEvento().getNombre().equalsIgnoreCase("Gol")) {

            Partido partido = evento.getPartido();

            if (evento.getEquipo().getId().equals(partido.getEquipoLocal().getId())) {
                partido.setGolesLocal(partido.getGolesLocal() + 1);
            } else {
                partido.setGolesVisitante(partido.getGolesVisitante() + 1);
            }

            partidoRepository.save(partido);
        }

        return nuevo;
    }

    public List<Evento> obtenerPorPartido(Long partidoId) {
        return eventoRepository.findByPartidoId(partidoId);
    }

    public List<Evento> obtenerPorJugador(Long jugadorId) {
        return eventoRepository.findByJugadorId(jugadorId);
    }

    public long golesPorJugador(Long jugadorId) {
        return eventoRepository.countByJugadorIdAndTipoEventoNombre(jugadorId, "Gol");
    }
}
