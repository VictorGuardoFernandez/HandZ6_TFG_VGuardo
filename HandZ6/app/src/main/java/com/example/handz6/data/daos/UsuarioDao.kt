package com.example.handz6.data.daos


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.handz6.data.Entities.Usuario
import kotlinx.coroutines.flow.Flow

// data/local/dao/UsuarioDao.kt
@Dao
interface UsuarioDao {
    @Query("SELECT * FROM usuario")
    fun getAll(): Flow<List<Usuario>>

    @Query("SELECT * FROM usuario WHERE id = :id")
    suspend fun getById(id: Long): Usuario?

    @Query("SELECT * FROM usuario WHERE username = :username AND password = :password LIMIT 1")
    suspend fun login(username: String, password: String): Usuario?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(usuario: Usuario)

    @Update
    suspend fun update(usuario: Usuario)

    @Delete
    suspend fun delete(usuario: Usuario)
}