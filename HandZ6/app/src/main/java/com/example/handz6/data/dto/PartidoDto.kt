package com.example.handz6.data.dto

data class PartidoDto(
    val id: Long,
    val fecha: Long? = null,
    val equipoLocalId: Long? = null,
    val equipoVisitanteId: Long? = null,
    val golesLocal: Int = 0,
    val golesVisitante: Int = 0,
    val estado: String? = null
)
