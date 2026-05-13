package com.example.handz6.data.relations
import androidx.room.Embedded
import androidx.room.Relation
import com.example.handz6.data.Entities.Evento
import com.example.handz6.data.Entities.Jugador
import com.example.handz6.data.local.entities.EventoTiro

// data/local/relations/EventoTiroCompleto.kt
data class EventoTiroCompleto(
    @Embedded val eventoTiro: EventoTiro,

    @Relation(parentColumn = "evento_id", entityColumn = "id")
    val evento: Evento,

    @Relation(parentColumn = "portero_id", entityColumn = "id")
    val portero: Jugador?
)