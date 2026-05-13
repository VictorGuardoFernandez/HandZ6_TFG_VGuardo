// controllers/EventoTiroController.java
package org.example.handz6api.controllers;

import org.example.handz6api.model.EventoTiro;
import org.example.handz6api.services.EventoTiroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evento-tiros")
public class EventoTiroController {

    private final EventoTiroService eventoTiroService;

    public EventoTiroController(EventoTiroService eventoTiroService) {
        this.eventoTiroService = eventoTiroService;
    }

    // POST /api/evento-tiros
    @PostMapping
    public ResponseEntity<EventoTiro> create(@RequestBody EventoTiro eventoTiro) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(eventoTiroService.guardar(eventoTiro));
    }

    // GET /api/evento-tiros/{id}
    @GetMapping("/{id}")
    public ResponseEntity<EventoTiro> getById(@PathVariable Long id) {
        return eventoTiroService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/evento-tiros/partido/{partidoId}
    @GetMapping("/partido/{partidoId}")
    public ResponseEntity<List<EventoTiro>> getByPartido(@PathVariable Long partidoId) {
        return ResponseEntity.ok(eventoTiroService.obtenerPorPartido(partidoId));
    }

    // GET /api/evento-tiros/jugador/{jugadorId}
    @GetMapping("/jugador/{jugadorId}")
    public ResponseEntity<List<EventoTiro>> getByJugador(@PathVariable Long jugadorId) {
        return ResponseEntity.ok(eventoTiroService.obtenerPorJugador(jugadorId));
    }

    // GET /api/evento-tiros/portero/{porteroId}
    @GetMapping("/portero/{porteroId}")
    public ResponseEntity<List<EventoTiro>> getByPortero(@PathVariable Long porteroId) {
        return ResponseEntity.ok(eventoTiroService.obtenerPorPortero(porteroId));
    }

    // GET /api/evento-tiros/partido/{partidoId}/resultado/{resultado}
    @GetMapping("/partido/{partidoId}/resultado/{resultado}")
    public ResponseEntity<Long> countByResultado(
            @PathVariable Long partidoId,
            @PathVariable String resultado
    ) {
        return ResponseEntity.ok(eventoTiroService.contarPorResultadoYPartido(partidoId, resultado));
    }

    // DELETE /api/evento-tiros/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (eventoTiroService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        eventoTiroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}