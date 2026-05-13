package com.example.handz6.data.dto

data class EventoDto(
    val id: Long,
    val minuto: Int,
    val partidoId: Long? = null,
    val jugadorId: Long? = null,
    val tipoEventoId: Long? = null,
    val equipoId: Long? = null
)
