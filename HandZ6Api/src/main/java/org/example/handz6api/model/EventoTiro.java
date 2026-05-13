// model/EventoTiro.java
package org.example.handz6api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.handz6api.model.enums.ResultadoTiro;
import org.example.handz6api.model.enums.TipoAtaque;
import org.example.handz6api.model.enums.ZonaPorteria;

@Getter
@Setter
@Entity
@Table(name = "evento_tiro")
public class EventoTiro {

    @Id
    @Column(name = "evento_id")
    private Long eventoId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "evento_id")
    private Evento evento;

    @Enumerated(EnumType.STRING)
    @Column(name = "zona", nullable = false)
    private ZonaPorteria zona;

    @Enumerated(EnumType.STRING)
    @Column(name = "resultado", nullable = false)
    private ResultadoTiro resultado;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_ataque", nullable = false)
    private TipoAtaque tipoAtaque;

    @ManyToOne
    @JoinColumn(name = "portero_id")
    private Jugador portero;


}