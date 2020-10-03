package qboinstitute.com.apptallerroomkotlin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import qboinstitute.com.apptallerroomkotlin.db.dao.TarjetaDao
import qboinstitute.com.apptallerroomkotlin.db.entity.TarjetaEntity


@Database(entities = [TarjetaEntity::class], version = 1)
public abstract  class TarjetaRoomDatabase : RoomDatabase() {

    abstract fun tarjetaDao(): TarjetaDao
    //Agrupa todos las variables y métodos que están definidos como
    //estáticos
    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        //Indicamos que el cambio que pueda tener esta variable
        //se vuelve inmediata para otros subprocesos
        @Volatile
        private var INSTANCE: TarjetaRoomDatabase? = null

        fun getDatabase(context: Context): TarjetaRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            //este método es utilizado sólo por un hilo de ejecución
            //haciendo que los demás hilos esperen.
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TarjetaRoomDatabase::class.java,
                    "tarjetasdb"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }


}