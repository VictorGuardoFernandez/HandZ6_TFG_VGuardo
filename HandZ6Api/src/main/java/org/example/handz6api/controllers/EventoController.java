// controllers/EventoController.java
package org.example.handz6api.controllers;

import org.example.handz6api.model.Evento;
import org.example.handz6api.services.EventoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    // POST /api/eventos
    // Registra el evento y actualiza el marcador automáticamente si es gol
    @PostMapping
    public ResponseEntity<Evento> registrar(@RequestBody Evento evento) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(eventoService.registrarEvento(evento));
    }

    // GET /api/eventos/partido/{partidoId}
    @GetMapping("/partido/{partidoId}")
    public ResponseEntity<List<Evento>> getByPartido(@PathVariable Long partidoId) {
        return ResponseEntity.ok(eventoService.obtenerPorPartido(partidoId));
    }

    // GET /api/eventos/jugador/{jugadorId}
    @GetMapping("/jugador/{jugadorId}")
    public ResponseEntity<List<Evento>> getByJugador(@PathVariable Long jugadorId) {
        return ResponseEntity.ok(eventoService.obtenerPorJugador(jugadorId));
    }

    // GET /api/eventos/jugador/{jugadorId}/goles
    @GetMapping("/jugador/{jugadorId}/goles")
    public ResponseEntity<Long> getGolesByJugador(@PathVariable Long jugadorId) {
        return ResponseEntity.ok(eventoService.golesPorJugador(jugadorId));
    }
}
