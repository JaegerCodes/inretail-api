package com.inretailapp.data.database

import androidx.room.Dao
import com.inretailapp.data.model.Configuraciones

@Dao
abstract class ConfiguracionesDao: BaseDao<Configuraciones>(Configuraciones::class.java)