package org.example.handz6api.services;

import org.example.handz6api.model.Jugador;
import org.example.handz6api.repositories.JugadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JugadorService {

    private final JugadorRepository jugadorRepository;

    public JugadorService(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    // 📌 Guardar o actualizar jugador
    public Jugador guardar(Jugador jugador) {
        return jugadorRepository.save(jugador);
    }

    // 📌 Obtener todos
    public List<Jugador> obtenerTodos() {
        return jugadorRepository.findAll();
    }

    // 📌 Buscar por ID
    public Optional<Jugador> obtenerPorId(Long id) {
        return jugadorRepository.findById(id);
    }

    // 📌 Eliminar
    public void eliminar(Long id) {
        jugadorRepository.deleteById(id);
    }

    // 📌 Jugadores por equipo (OPTIMIZADO)
    public List<Jugador> obtenerPorEquipo(Long equipoId) {
        return jugadorRepository.findByEquipoId(equipoId);
    }

    // 📌 Buscar por dorsal en equipo (OPTIMIZADO)
    public Optional<Jugador> buscarPorEquipoYdorsal(Long equipoId, Integer dorsal) {
        return jugadorRepository.findByEquipoIdAndDorsal(equipoId, dorsal);
    }
}