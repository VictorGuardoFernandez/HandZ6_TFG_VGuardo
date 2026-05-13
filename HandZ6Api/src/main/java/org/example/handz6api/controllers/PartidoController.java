// controllers/PartidoController.java
package org.example.handz6api.controllers;

import org.example.handz6api.model.Partido;
import org.example.handz6api.services.PartidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/partidos")
public class PartidoController {

    private final PartidoService partidoService;

    public PartidoController(PartidoService partidoService) {
        this.partidoService = partidoService;
    }

    // GET /api/partidos
    @GetMapping
    public ResponseEntity<List<Partido>> getAll() {
        return ResponseEntity.ok(partidoService.obtenerTodos());
    }

    // GET /api/partidos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Partido> getById(@PathVariable Long id) {
        return partidoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/partidos
    @PostMapping
    public ResponseEntity<Partido> create(@RequestBody Partido partido) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(partidoService.guardarPartido(partido));
    }

    // PUT /api/partidos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Partido> update(@PathVariable Long id, @RequestBody Partido partido) {
        return partidoService.obtenerPorId(id)
                .map(existing -> {
                    partido.setId(id);
                    return ResponseEntity.ok(partidoService.guardarPartido(partido));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // PATCH /api/partidos/{id}/marcador
    @PatchMapping("/{id}/marcador")
    public ResponseEntity<Partido> updateMarcador(
            @PathVariable Long id,
            @RequestBody Map<String, Integer> body
    ) {
        try {
            Integer golesLocal = body.get("golesLocal");
            Integer golesVisitante = body.get("golesVisitante");
            return ResponseEntity.ok(partidoService.actualizarMarcador(id, golesLocal, golesVisitante));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // PATCH /api/partidos/{id}/estado
    @PatchMapping("/{id}/estado")
    public ResponseEntity<Partido> updateEstado(
            @PathVariable Long id,
            @RequestBody Map<String, String> body
    ) {
        try {
            return ResponseEntity.ok(partidoService.actualizarEstado(id, body.get("estado")));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE /api/partidos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (partidoService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        partidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
