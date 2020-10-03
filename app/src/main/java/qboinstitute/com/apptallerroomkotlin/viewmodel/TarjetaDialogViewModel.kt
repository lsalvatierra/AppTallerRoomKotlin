package qboinstitute.com.apptallerroomkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import qboinstitute.com.apptallerroomkotlin.db.TarjetaRoomDatabase
import qboinstitute.com.apptallerroomkotlin.db.entity.TarjetaEntity
import qboinstitute.com.apptallerroomkotlin.repositorio.TarjetaRepository

class TarjetaDialogViewModel (application: Application)
    : AndroidViewModel(application) {
    // TODO: Implement the ViewModel
    private val repository: TarjetaRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    init {
        val tarjetaDao = TarjetaRoomDatabase.getDatabase(application).tarjetaDao()
        repository = TarjetaRepository(tarjetaDao)
    }
    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insertar(tarjetaEntity: TarjetaEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insertar(tarjetaEntity)
    }


    fun listarTarjetas() : LiveData<List<TarjetaEntity>> {
        return repository.listarTarjetas()
    }
    /*
    fun actualizar(tarjetaEntity: TarjetaEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.actualizar(tarjetaEntity)
    }

    fun eliminarPorID(idtarjeta: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.eliminarPorID(idtarjeta)
    }

    fun obtenerTarjeta(idtarjeta: Int) : LiveData<TarjetaEntity> {
        return repository.obtenerTarjeta(idtarjeta)
    }
     */

}