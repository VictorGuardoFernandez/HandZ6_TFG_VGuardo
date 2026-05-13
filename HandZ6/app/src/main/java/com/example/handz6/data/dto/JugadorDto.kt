package com.example.handz6.data.dto

data class JugadorDto(
    val id: Long,
    val nombre: String,
    val dorsal: Int,
    val posicion: String? = null,
    val equipoId: Long? = null
)
