// controllers/TipoEventoController.java
package org.example.handz6api.controllers;

import org.example.handz6api.model.TipoEvento;
import org.example.handz6api.services.TipoEventoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipo-eventos")
public class TipoEventoController {

    private final TipoEventoService tipoEventoService;

    public TipoEventoController(TipoEventoService tipoEventoService) {
        this.tipoEventoService = tipoEventoService;
    }

    // GET /api/tipo-eventos
    @GetMapping
    public ResponseEntity<List<TipoEvento>> getAll() {
        return ResponseEntity.ok(tipoEventoService.obtenerTodos());
    }

    // GET /api/tipo-eventos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<TipoEvento> getById(@PathVariable Long id) {
        return tipoEventoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/tipo-eventos/nombre/{nombre}
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<TipoEvento> getByNombre(@PathVariable String nombre) {
        return tipoEventoService.buscarPorNombre(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/tipo-eventos
    @PostMapping
    public ResponseEntity<TipoEvento> create(@RequestBody TipoEvento tipoEvento) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tipoEventoService.guardar(tipoEvento));
    }

    // PUT /api/tipo-eventos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<TipoEvento> update(@PathVariable Long id, @RequestBody TipoEvento tipoEvento) {
        return tipoEventoService.obtenerPorId(id)
                .map(existing -> {
                    tipoEvento.setId(id);
                    return ResponseEntity.ok(tipoEventoService.guardar(tipoEvento));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /api/tipo-eventos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (tipoEventoService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        tipoEventoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
