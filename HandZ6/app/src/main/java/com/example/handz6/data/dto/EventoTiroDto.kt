package com.example.handz6.data.dto

data class EventoTiroDto(
    val eventoId: Long,
    val zona: String,
    val resultado: String,
    val tipoAtaque: String,
    val porteroId: Long? = null
)
