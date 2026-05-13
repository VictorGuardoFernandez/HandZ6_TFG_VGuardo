// controllers/EquipoController.java
package org.example.handz6api.controllers;

import org.example.handz6api.model.Equipo;
import org.example.handz6api.services.EquipoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/equipos")
public class EquipoController {

    private final EquipoService equipoService;

    public EquipoController(EquipoService equipoService) {
        this.equipoService = equipoService;
    }

    // GET /api/equipos
    @GetMapping
    public ResponseEntity<List<Equipo>> getAll() {
        return ResponseEntity.ok(equipoService.obtenerTodos());
    }

    // GET /api/equipos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Equipo> getById(@PathVariable Long id) {
        return equipoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST /api/equipos
    @PostMapping
    public ResponseEntity<Equipo> create(@RequestBody Equipo equipo) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(equipoService.guardar(equipo));
    }

    // PUT /api/equipos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Equipo> update(@PathVariable Long id, @RequestBody Equipo equipo) {
        return equipoService.obtenerPorId(id)
                .map(existing -> {
                    equipo.setId(id);
                    return ResponseEntity.ok(equipoService.guardar(equipo));
                })
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping(value = "/{id}/logo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Equipo> subirLogo(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file
    ) {
        try {
            // Valida por extensión en lugar de contentType
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null || (!originalFilename.toLowerCase().endsWith(".jpg")
                    && !originalFilename.toLowerCase().endsWith(".jpeg")
                    && !originalFilename.toLowerCase().endsWith(".png")
                    && !originalFilename.toLowerCase().endsWith(".gif"))) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(equipoService.subirLogo(id, file));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // DELETE /api/equipos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (equipoService.obtenerPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        equipoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}