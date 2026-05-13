package org.example.handz6api.repositories;

import org.example.handz6api.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {

    // 📌 Jugadores por equipo
    List<Jugador> findByEquipoId(Long equipoId);

    // 📌 Jugador por equipo y dorsal
    Optional<Jugador> findByEquipoIdAndDorsal(Long equipoId, Integer dorsal);
}