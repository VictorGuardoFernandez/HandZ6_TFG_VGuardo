// repositories/EventoTiroRepository.java
package org.example.handz6api.repositories;

import org.example.handz6api.model.EventoTiro;
import org.example.handz6api.model.enums.ResultadoTiro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoTiroRepository extends JpaRepository<EventoTiro, Long> {
    List<EventoTiro> findByEventoPartidoId(Long partidoId);
    List<EventoTiro> findByEventoJugadorId(Long jugadorId);
    List<EventoTiro> findByPorteroId(Long porteroId);
    long countByEventoPartidoIdAndResultado(Long partidoId, ResultadoTiro resultado);
}