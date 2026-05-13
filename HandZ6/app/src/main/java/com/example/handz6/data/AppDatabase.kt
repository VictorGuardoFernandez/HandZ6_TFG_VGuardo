package com.example.handz6.data


import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Room
import androidx.room.TypeConverters
import com.example.handz6.data.Entities.Equipo
import com.example.handz6.data.Entities.Evento
import com.example.handz6.data.Entities.Jugador
import com.example.handz6.data.Entities.Partido
import com.example.handz6.data.Entities.Rol
import com.example.handz6.data.Entities.TipoEvento
import com.example.handz6.data.Entities.Usuario
import com.example.handz6.data.daos.EquipoDao
import com.example.handz6.data.daos.EventoDao
import com.example.handz6.data.daos.EventoTiroDao
import com.example.handz6.data.daos.JugadorDao
import com.example.handz6.data.daos.PartidoDao
import com.example.handz6.data.daos.RolDao
import com.example.handz6.data.daos.TipoEventoDao
import com.example.handz6.data.daos.UsuarioDao
import com.example.handz6.data.local.entities.EventoTiro

// data/local/AppDatabase.kt
@Database(
    entities = [
        Usuario::class,
        Rol::class,
        Equipo::class,
        Jugador::class,
        Partido::class,
        Evento::class,
        TipoEvento::class,
        EventoTiro::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun rolDao(): RolDao
    abstract fun equipoDao(): EquipoDao
    abstract fun jugadorDao(): JugadorDao
    abstract fun partidoDao(): PartidoDao
    abstract fun eventoDao(): EventoDao
    abstract fun tipoEventoDao(): TipoEventoDao
    abstract fun eventoTiroDao(): EventoTiroDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "handball_stats.db"
                )
                    .fallbackToDestructiveMigration() // solo en desarrollo
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}