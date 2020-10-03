package qboinstitute.com.apptallerroomkotlin.repositorio

import androidx.lifecycle.LiveData
import qboinstitute.com.apptallerroomkotlin.db.dao.TarjetaDao
import qboinstitute.com.apptallerroomkotlin.db.entity.TarjetaEntity

class TarjetaRepository (private val tarjetaDao: TarjetaDao) {

    suspend fun insertar(tarjetaEntity: TarjetaEntity){
        tarjetaDao.insertar(tarjetaEntity)
    }
    fun listarTarjetas():
            LiveData<List<TarjetaEntity>> {
        return tarjetaDao.listarTarjetas()
    }
    /*
    suspend fun actualizar(tarjetaEntity: TarjetaEntity){
        tarjetaDao.actualizar(tarjetaEntity)
    }

    suspend fun eliminarPorID(idtarjeta: Int){
        tarjetaDao.eliminarPorID(idtarjeta)
    }

    fun obtenerTarjeta(idtarjeta: Int):
            LiveData<TarjetaEntity> {
        return tarjetaDao.obtenerTarjeta(idtarjeta)
    }
    */


}