package com.example.handz6.data.mapper

import com.example.handz6.data.Entities.Equipo
import com.example.handz6.data.Entities.Evento
import com.example.handz6.data.Entities.Jugador
import com.example.handz6.data.Entities.Partido
import com.example.handz6.data.Entities.Rol
import com.example.handz6.data.Entities.TipoEvento
import com.example.handz6.data.Entities.Usuario
import com.example.handz6.data.dto.EquipoDto
import com.example.handz6.data.dto.EventoDto
import com.example.handz6.data.dto.EventoTiroDto
import com.example.handz6.data.dto.JugadorDto
import com.example.handz6.data.dto.PartidoDto
import com.example.handz6.data.dto.RolDto
import com.example.handz6.data.dto.TipoEventoDto
import com.example.handz6.data.dto.UsuarioDto
import com.example.handz6.data.local.entities.EventoTiro
import com.example.handz6.data.local.enums.PosicionJugador
import com.example.handz6.data.local.enums.ResultadoTiro
import com.example.handz6.data.local.enums.TipoAtaque
import com.example.handz6.data.local.enums.ZonaPorteria

// data/remote/mapper/Mappers.kt
fun EquipoDto.toEntity() = Equipo(
    id = id,
    nombre = nombre,
    logoUrl = logoUrl
)

fun JugadorDto.toEntity() = Jugador(
    id = id,
    nombre = nombre,
    dorsal = dorsal,
    posicion = posicion?.let { PosicionJugador.valueOf(it) },
    equipoId = equipoId
)

fun PartidoDto.toEntity() = Partido(
    id = id,
    fecha = fecha,
    equipoLocalId = equipoLocalId,
    equipoVisitanteId = equipoVisitanteId,
    golesLocal = golesLocal,
    golesVisitante = golesVisitante,
    estado = estado
)

fun EventoDto.toEntity() = Evento(
    id = id,
    minuto = minuto,
    partidoId = partidoId,
    jugadorId = jugadorId,
    tipoEventoId = tipoEventoId,
    equipoId = equipoId
)

fun EventoTiroDto.toEntity() = EventoTiro(
    eventoId = eventoId,
    zona = ZonaPorteria.valueOf(zona),
    resultado = ResultadoTiro.valueOf(resultado),
    tipoAtaque = TipoAtaque.valueOf(tipoAtaque),
    porteroId = porteroId
)
// Añade en data/remote/mapper/Mappers.kt
fun UsuarioDto.toEntity() = Usuario(
    id = id,
    username = username,
    password = password,
    email = email,
    rolId = rolId,
    jugadorId = jugadorId,
    equipoId = equipoId
)

fun RolDto.toEntity() = Rol(
    id = id,
    nombre = nombre
)

fun TipoEventoDto.toEntity() = TipoEvento(
    id = id,
    nombre = nombre
)
// Entity → Dto
fun Equipo.toDto() = EquipoDto(
    id = id,
    nombre = nombre,
    logoUrl = logoUrl
)

fun Jugador.toDto() = JugadorDto(
    id = id,
    nombre = nombre,
    dorsal = dorsal,
    posicion = posicion?.name,
    equipoId = equipoId
)

fun Partido.toDto() = PartidoDto(
    id = id,
    fecha = fecha,
    equipoLocalId = equipoLocalId,
    equipoVisitanteId = equipoVisitanteId,
    golesLocal = golesLocal,
    golesVisitante = golesVisitante,
    estado = estado
)

fun Evento.toDto() = EventoDto(
    id = id,
    minuto = minuto,
    partidoId = partidoId,
    jugadorId = jugadorId,
    tipoEventoId = tipoEventoId,
    equipoId = equipoId
)

fun EventoTiro.toDto() = EventoTiroDto(
    eventoId = eventoId,
    zona = zona.name,
    resultado = resultado.name,
    tipoAtaque = tipoAtaque.name,
    porteroId = porteroId
)

fun Usuario.toDto() = UsuarioDto(
    id = id,
    username = username,
    password = password,
    email = email,
    rolId = rolId,
    jugadorId = jugadorId,
    equipoId = equipoId
)

fun Rol.toDto() = RolDto(
    id = id,
    nombre = nombre
)

fun TipoEvento.toDto() = TipoEventoDto(
    id = id,
    nombre = nombre
)