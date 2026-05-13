package com.example.handz6.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.handz6.data.Entities.Equipo
import kotlinx.coroutines.flow.Flow

// data/local/dao/EquipoDao.kt
@Dao
interface EquipoDao {
    @Query("SELECT * FROM equipo")
    fun getAll(): Flow<List<Equipo>>

    @Query("SELECT * FROM equipo WHERE id = :id")
    suspend fun getById(id: Long): Equipo?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(equipo: Equipo)

    @Delete
    suspend fun delete(equipo: Equipo)
}