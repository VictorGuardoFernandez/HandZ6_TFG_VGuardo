package com.example.handz6.data.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.handz6.data.local.enums.PosicionJugador

// Jugador.kt
@Entity(
    tableName = "jugador",
    foreignKeys = [
        ForeignKey(
            entity = Equipo::class,
            parentColumns = ["id"],
            childColumns = ["equipo_id"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        Index(value = ["equipo_id"])
    ]
)
data class Jugador(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "nombre")
    val nombre: String,

    @ColumnInfo(name = "dorsal")
    val dorsal: Int,

    @ColumnInfo(name = "equipo_id")
    val equipoId: Long? = null,

    @ColumnInfo(name = "posicion")
    val posicion: PosicionJugador? = null,
)
