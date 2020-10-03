package qboinstitute.com.apptallerroomkotlin.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import qboinstitute.com.apptallerroomkotlin.db.entity.TarjetaEntity


@Dao
interface TarjetaDao {

    @Insert
    suspend fun insertar(tarjeta: TarjetaEntity?)
    /*
    @Update
    suspend fun actualizar(tarjeta: TarjetaEntity?)

    @Query("DELETE FROM tarjeta WHERE id = :idtarjeta")
    suspend fun eliminarPorID(idtarjeta: Int)

    @Query("SELECT * FROM tarjeta WHERE id = :idtarjeta")
    fun obtenerTarjeta(idtarjeta: Int): LiveData<TarjetaEntity>

     */
    @Query("SELECT * FROM tarjeta ORDER BY titulo ASC")
    fun listarTarjetas(): LiveData<List<TarjetaEntity>>

}