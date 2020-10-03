package qboinstitute.com.apptallerroomkotlin.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tarjeta")
data class TarjetaEntity(
    @PrimaryKey(autoGenerate = true)
    val  id : Int,
    val titulo: String,
    val contenido:  String,
    val importe :  Boolean,
    val color: String
)