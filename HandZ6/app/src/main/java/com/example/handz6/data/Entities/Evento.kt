package com.example.handz6.data.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

// Evento.kt
@Entity(
    tableName = "evento",
    foreignKeys = [
        ForeignKey(
            entity = Partido::class,
            parentColumns = ["id"],
            childColumns = ["partido_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Jugador::class,
            parentColumns = ["id"],
            childColumns = ["jugador_id"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = TipoEvento::class,
            parentColumns = ["id"],
            childColumns = ["tipo_evento_id"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Equipo::class,
            parentColumns = ["id"],
            childColumns = ["equipo_id"],
            onDelete = ForeignKey.NO_ACTION
        )
    ],
    indices = [
        Index(value = ["partido_id"]),
        Index(value = ["jugador_id"]),
        Index(value = ["tipo_evento_id"]),
        Index(value = ["equipo_id"])
    ]
)
data class Evento(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "minuto")
    val minuto: Int,

    @ColumnInfo(name = "partido_id")
    val partidoId: Long? = null,

    @ColumnInfo(name = "jugador_id")
    val jugadorId: Long? = null,

    @ColumnInfo(name = "tipo_evento_id")
    val tipoEventoId: Long? = null,

    @ColumnInfo(name = "equipo_id")
    val equipoId: Long? = null
)
