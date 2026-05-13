package com.example.handz6.data


import androidx.room.TypeConverter
import com.example.handz6.data.local.enums.PosicionJugador
import com.example.handz6.data.local.enums.ResultadoTiro
import com.example.handz6.data.local.enums.TipoAtaque
import com.example.handz6.data.local.enums.ZonaPorteria
import java.time.Instant

// Converters.kt
class Converters {
    @TypeConverter
    fun fromInstant(value: Long?): Instant? = value?.let { Instant.ofEpochMilli(it) }

    @TypeConverter
    fun toInstant(instant: Instant?): Long? = instant?.toEpochMilli()
    // Añadir a Converters.kt
    @TypeConverter
    fun fromZonaPorteria(zona: ZonaPorteria?): String? = zona?.name

    @TypeConverter
    fun toZonaPorteria(zona: String?): ZonaPorteria? = zona?.let { ZonaPorteria.valueOf(it) }

    @TypeConverter
    fun fromResultadoTiro(resultado: ResultadoTiro?): String? = resultado?.name

    @TypeConverter
    fun toResultadoTiro(resultado: String?): ResultadoTiro? = resultado?.let { ResultadoTiro.valueOf(it) }

    @TypeConverter
    fun fromTipoAtaque(tipo: TipoAtaque?): String? = tipo?.name

    @TypeConverter
    fun toTipoAtaque(tipo: String?): TipoAtaque? = tipo?.let { TipoAtaque.valueOf(it) }
    // Añade en Converters.kt
    @TypeConverter
    fun fromPosicionJugador(posicion: PosicionJugador?): String? = posicion?.name

    @TypeConverter
    fun toPosicionJugador(posicion: String?): PosicionJugador? = posicion?.let { PosicionJugador.valueOf(it) }
}