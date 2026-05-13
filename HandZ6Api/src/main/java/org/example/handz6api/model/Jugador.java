package org.example.handz6api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.handz6api.model.enums.PosicionJugador;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "jugador")
public class Jugador {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "dorsal", nullable = false)
    private Integer dorsal;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;

    // Añade en model/Jugador.java
    @Enumerated(EnumType.STRING)
    @Column(name = "posicion")
    private PosicionJugador posicion;


}