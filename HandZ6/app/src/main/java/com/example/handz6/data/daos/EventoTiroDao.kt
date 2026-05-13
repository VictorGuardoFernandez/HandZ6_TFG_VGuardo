package com.example.handz6.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.handz6.data.local.entities.EventoTiro
import com.example.handz6.data.relations.EventoTiroCompleto
import kotlinx.coroutines.flow.Flow

// data/local/dao/EventoTiroDao.kt
@Dao
interface EventoTiroDao {

    @Query("SELECT * FROM evento_tiro WHERE evento_id = :eventoId")
    suspend fun getById(eventoId: Long): EventoTiro?

    @Query("""
        SELECT et.* FROM evento_tiro et
        INNER JOIN evento e ON et.evento_id = e.id
        WHERE e.partido_id = :partidoId
    """)
    fun getByPartido(partidoId: Long): Flow<List<EventoTiro>>

    @Query("""
        SELECT et.* FROM evento_tiro et
        INNER JOIN evento e ON et.evento_id = e.id
        WHERE e.jugador_id = :jugadorId
    """)
    fun getByJugador(jugadorId: Long): Flow<List<EventoTiro>>

    @Query("SELECT * FROM evento_tiro WHERE portero_id = :porteroId")
    fun getByPortero(porteroId: Long): Flow<List<EventoTiro>>

    @Query("""
        SELECT COUNT(*) FROM evento_tiro et
        INNER JOIN evento e ON et.evento_id = e.id
        WHERE e.partido_id = :partidoId AND et.resultado = :resultado
    """)
    suspend fun countByPartidoYResultado(partidoId: Long, resultado: String): Int
    @Transaction
    @Query("""
    SELECT et.* FROM evento_tiro et
    INNER JOIN evento e ON et.evento_id = e.id
    WHERE e.partido_id = :partidoId
""")
    fun getByPartidoCompleto(partidoId: Long): Flow<List<EventoTiroCompleto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(eventoTiro: EventoTiro)

    @Delete
    suspend fun delete(eventoTiro: EventoTiro)
}