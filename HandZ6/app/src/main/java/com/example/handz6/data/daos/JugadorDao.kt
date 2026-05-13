package com.example.handz6.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.handz6.data.Entities.Jugador
import kotlinx.coroutines.flow.Flow

// data/local/dao/JugadorDao.kt
@Dao
interface JugadorDao {
    @Query("SELECT * FROM jugador")
    fun getAll(): Flow<List<Jugador>>

    @Query("SELECT * FROM jugador WHERE equipo_id = :equipoId")
    fun getByEquipo(equipoId: Long): Flow<List<Jugador>>

    @Query("SELECT * FROM jugador WHERE id = :id")
    suspend fun getById(id: Long): Jugador?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(jugador: Jugador)

    @Update
    suspend fun update(jugador: Jugador)

    @Delete
    suspend fun delete(jugador: Jugador)
}