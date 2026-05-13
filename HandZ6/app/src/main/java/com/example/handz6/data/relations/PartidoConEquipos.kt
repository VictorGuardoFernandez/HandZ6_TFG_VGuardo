package com.example.handz6.data.relations


import androidx.room.Embedded
import androidx.room.Relation
import com.example.handz6.data.Entities.Equipo
import com.example.handz6.data.Entities.Partido

data class PartidoConEquipos(
    @Embedded val partido: Partido,

    @Relation(
        parentColumn = "equipo_local_id",
        entityColumn = "id"
    )
    val equipoLocal: Equipo?,

    @Relation(
        parentColumn = "equipo_visitante_id",
        entityColumn = "id"
    )
    val equipoVisitante: Equipo?
)
