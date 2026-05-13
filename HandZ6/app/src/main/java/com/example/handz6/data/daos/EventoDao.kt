package com.example.handz6.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.handz6.data.Entities.Evento
import com.example.handz6.data.relations.EventoCompleto
import kotlinx.coroutines.flow.Flow

// data/local/dao/EventoDao.kt
@Dao
interface EventoDao {
    @Query("SELECT * FROM evento WHERE partido_id = :partidoId")
    fun getByPartido(partidoId: Long): Flow<List<Evento>>

    @Query("SELECT * FROM evento WHERE partido_id = :partidoId AND equipo_id = :equipoId")
    fun getByPartidoYEquipo(partidoId: Long, equipoId: Long): Flow<List<Evento>>

    // Stats de temporada por equipo
    @Query("""
        SELECT COUNT(*) FROM evento 
        WHERE equipo_id = :equipoId AND tipo_evento_id = :tipoEventoId
    """)
    suspend fun countByEquipoYTipo(equipoId: Long, tipoEventoId: Long): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(evento: Evento)

    @Delete
    suspend fun delete(evento: Evento)
    @Transaction
    @Query("SELECT * FROM evento WHERE partido_id = :partidoId")
    fun getEventosCompletos(partidoId: Long): Flow<List<EventoCompleto>>
}