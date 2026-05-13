package com.example.handz6.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.handz6.data.Entities.Partido
import com.example.handz6.data.relations.PartidoConEquipos
import kotlinx.coroutines.flow.Flow

// data/local/dao/PartidoDao.kt
@Dao
interface PartidoDao {
    @Query("SELECT * FROM partido ORDER BY fecha DESC")
    fun getAll(): Flow<List<Partido>>

    @Query("SELECT * FROM partido WHERE id = :id")
    suspend fun getById(id: Long): Partido?

    @Query("SELECT * FROM partido WHERE estado = :estado")
    fun getByEstado(estado: String): Flow<List<Partido>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(partido: Partido)

    @Update
    suspend fun update(partido: Partido)

    @Delete
    suspend fun delete(partido: Partido)
    @Transaction
    @Query("SELECT * FROM partido ORDER BY fecha DESC")
    fun getAllConEquipos(): Flow<List<PartidoConEquipos>>
}