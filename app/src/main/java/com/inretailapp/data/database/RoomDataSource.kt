package com.inretailapp.data.database

import com.inretailapp.app.providers.DatabaseProvider
import com.inretailapp.data.model.Configuraciones

class RoomDataSource(private val db: DatabaseProvider): LocalDataSource {

    override fun configuraciones(): ConfiguracionesDao = db.configuracionesDao
}

interface LocalDataSource {
    fun configuraciones(): ConfiguracionesDao
}


