package org.example.handz6api.repositories;

import org.example.handz6api.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByPartidoId(Long partidoId);

    List<Evento> findByJugadorId(Long jugadorId);

    long countByJugadorIdAndTipoEventoNombre(Long jugadorId, String nombre);
}