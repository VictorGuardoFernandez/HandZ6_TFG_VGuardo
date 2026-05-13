package com.example.handz6.data.dto

data class UsuarioDto(
    val id: Long,
    val username: String,
    val password: String,
    val email: String? = null,
    val rolId: Long,
    val jugadorId: Long? = null,
    val equipoId: Long? = null
)

