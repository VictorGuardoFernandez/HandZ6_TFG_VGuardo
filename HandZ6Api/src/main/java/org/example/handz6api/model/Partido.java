package org.example.handz6api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "partido")
public class Partido {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "fecha")
    private Instant fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_local_id")
    private Equipo equipoLocal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_visitante_id")
    private Equipo equipoVisitante;

    @ColumnDefault("0")
    @Column(name = "goles_local")
    private Integer golesLocal;

    @ColumnDefault("0")
    @Column(name = "goles_visitante")
    private Integer golesVisitante;

    @Column(name = "estado", length = 20)
    private String estado;

}