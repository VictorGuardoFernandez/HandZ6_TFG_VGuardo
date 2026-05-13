// entities/EventoTiro.kt
package com.example.handz6.data.local.entities

import androidx.room.*
import com.example.handz6.data.Entities.Evento
import com.example.handz6.data.Entities.Jugador

import com.example.handz6.data.local.enums.ResultadoTiro
import com.example.handz6.data.local.enums.TipoAtaque
import com.example.handz6.data.local.enums.ZonaPorteria

@Entity(
    tableName = "evento_tiro",
    foreignKeys = [
        ForeignKey(
            entity = Evento::class,
            parentColumns = ["id"],
            childColumns = ["evento_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Jugador::class,
            parentColumns = ["id"],
            childColumns = ["portero_id"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        Index(value = ["portero_id"])
    ]
)
data class EventoTiro(
    @PrimaryKey
    @ColumnInfo(name = "evento_id")
    val eventoId: Long,

    @ColumnInfo(name = "zona")
    val zona: ZonaPorteria,

    @ColumnInfo(name = "resultado")
    val resultado: ResultadoTiro,

    @ColumnInfo(name = "tipo_ataque")
    val tipoAtaque: TipoAtaque,

    @ColumnInfo(name = "portero_id")
    val porteroId: Long? = null
)