package com.example.handz6.data.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.handz6.data.Entities.Equipo
import com.example.handz6.data.Entities.Evento
import com.example.handz6.data.Entities.Jugador
import com.example.handz6.data.Entities.TipoEvento

data class EventoCompleto(
    @Embedded val evento: Evento,

    @Relation(parentColumn = "jugador_id", entityColumn = "id")
    val jugador: Jugador?,

    @Relation(parentColumn = "tipo_evento_id", entityColumn = "id")
    val tipoEvento: TipoEvento?,

    @Relation(parentColumn = "equipo_id", entityColumn = "id")
    val equipo: Equipo?
)