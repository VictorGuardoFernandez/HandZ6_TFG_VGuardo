// controllers/JugadorController.java
package org.example.handz6api.controllers;

import org.example.handz6api.model.Jugador;
import org.example.handz6api.services.JugadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jugadores")
public class JugadorController {

    private final JugadorService jugadorService;

    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    // GET /api/jugadores
    @GetMapping
    public ResponseEntity<List<Jugador>> getAll() {
        return ResponseEntity.ok(jugadorService.obtenerTodos());
    }

    // GET /api/jugadores/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Jugador> getById(@PathVariable Long id) {
        return jugadorService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/jugadores/equipo/{equipoId}
    @GetMapping("/equipo/{equipoId}")
    public ResponseEntity<List<Jugador>> getByEquipo(@PathVariable Long equipoId) {
        return ResponseEntity.ok(jugadorService.obtenerPorEquipo(equipoId));
    }

    // GET /api/jugadores/equipo/{equipoId}/dorsal/{dorsal}
    @GetMapping("/equipo/{equipoId}/dorsal/{dorsal}")
    public ResponseEntity<Jugador> getByEquipoYDorsal(
            @PathVariable Long equipoId,
            @PathVariable Integer dorsal
    ) {
        return jugadorService.buscarPorEquipoYdorsal(equipoId, dorsal)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/jugadores
    @PostMapping
    public ResponseEntity<Jugador> create(@RequestBody Jugador jugador) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(jugadorService.guardar(jugador));
    }

    // PUT /api/jugadores/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Jugador> update(@PathVariable Long id, @RequestBody Jugador jugador) {
        return jugadorService.obtenerPorId(id)
                .map(existing -> {
                    jugador.setId(id);
                    return ResponseEntity.ok(jugadorService.guardar(jugador));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/jugadores/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (jugadorService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        jugadorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
