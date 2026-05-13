package com.example.handz6.data.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

// Equipo.kt
@Entity(tableName = "equipo")
data class Equipo(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

    @ColumnInfo(name = "nombre")
    val nombre: String,

    @ColumnInfo(name = "logo_url")
    val logoUrl: String? = null  // null si el equipo no tiene logo
)
