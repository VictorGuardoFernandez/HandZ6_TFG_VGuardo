package com.example.handz6.data.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

// Partido.kt
@Entity(
    tableName = "partido",
    foreignKeys = [
        ForeignKey(
            entity = Equipo::class,
            parentColumns = ["id"],
            childColumns = ["equipo_local_id"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Equipo::class,
            parentColumns = ["id"],
            childColumns = ["equipo_visitante_id"],
            onDelete = ForeignKey.NO_ACTION
        )
    ],
    indices = [
        Index(value = ["equipo_local_id"]),
        Index(value = ["equipo_visitante_id"])
    ]
)
data class Partido(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "fecha")
    val fecha: Long? = null, // Instant → Long (epoch millis)

    @ColumnInfo(name = "equipo_local_id")
    val equipoLocalId: Long? = null,

    @ColumnInfo(name = "equipo_visitante_id")
    val equipoVisitanteId: Long? = null,

    @ColumnInfo(name = "goles_local")
    val golesLocal: Int = 0,

    @ColumnInfo(name = "goles_visitante")
    val golesVisitante: Int = 0,

    @ColumnInfo(name = "estado")
    val estado: String? = null
)
