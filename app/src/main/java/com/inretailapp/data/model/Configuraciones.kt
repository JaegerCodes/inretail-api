package com.inretailapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "configuraciones")
data class Configuraciones(
    var nombreUsuario       : String,
    var token               : String,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}