package com.example.handz6.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.handz6.data.Entities.TipoEvento
import kotlinx.coroutines.flow.Flow

// data/local/dao/TipoEventoDao.kt
@Dao
interface TipoEventoDao {
    @Query("SELECT * FROM tipo_evento")
    fun getAll(): Flow<List<TipoEvento>>

    @Query("SELECT * FROM tipo_evento WHERE id = :id")
    suspend fun getById(id: Long): TipoEvento?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tipoEvento: TipoEvento)
}