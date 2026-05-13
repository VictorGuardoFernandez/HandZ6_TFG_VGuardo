package com.example.handz6.data.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey


// Usuario.kt
@Entity(
    tableName = "usuario",
    foreignKeys = [
        ForeignKey(
            entity = Rol::class,
            parentColumns = ["id"],
            childColumns = ["rol_id"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Jugador::class,
            parentColumns = ["id"],
            childColumns = ["jugador_id"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = Equipo::class,
            parentColumns = ["id"],
            childColumns = ["equipo_id"],
            onDelete = ForeignKey.SET_NULL  // 👈 añade esto
        )
    ],
    indices = [
        Index(value = ["rol_id"]),
        Index(value = ["jugador_id"]),
        Index(value = ["equipo_id"])  // 👈 añade esto
    ]
)
data class Usuario(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "password")
    val password: String,

    @ColumnInfo(name = "email")
    val email: String? = null,

    @ColumnInfo(name = "rol_id")
    val rolId: Long,

    @ColumnInfo(name = "jugador_id")
    val jugadorId: Long? = null,

    @ColumnInfo(name = "equipo_id")
    val equipoId: Long? = null  // null si es administrador
)
