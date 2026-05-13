package com.example.handz6.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.handz6.data.Entities.Rol
import kotlinx.coroutines.flow.Flow

// data/local/dao/RolDao.kt
@Dao
interface RolDao {
    @Query("SELECT * FROM rol")
    fun getAll(): Flow<List<Rol>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(rol: Rol)
}