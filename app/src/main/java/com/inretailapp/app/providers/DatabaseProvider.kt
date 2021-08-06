package com.inretailapp.app.providers

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.inretailapp.data.database.ConfiguracionesDao
import com.inretailapp.data.model.Configuraciones

@Database(
    version = 1, exportSchema = false,
    entities = [
        Configuraciones::class
    ],
)
abstract class DatabaseProvider : RoomDatabase() {
    abstract val configuracionesDao: ConfiguracionesDao
}

fun provideConfiguracionDao(database: DatabaseProvider) : ConfiguracionesDao {
    return database.configuracionesDao
}

fun provideDatabase(application: Application) : DatabaseProvider {
    return Room.databaseBuilder(application, DatabaseProvider::class.java, "sdy.database")
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()
}