package com.inretailapp.data.database

import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.inretailapp.data.model.Configuraciones
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
abstract class BaseDao<T>(private val entityClass: Class<T>) {

    // GENERIC QUERIES
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertar(entidad: T?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertar(lista: List<T>)

    @Delete
    abstract suspend fun eliminar(entidad: T)

    @Delete
    abstract suspend fun eliminar(lista: List<T>)

    suspend fun obtenerLista(): List<T> {
        val query = SimpleSQLiteQuery("SELECT * FROM ${DatabaseService(entityClass).getTableName()}")
        return queryBuscarTodos(query)
    }

    suspend fun eliminarLista(): Int {
        val query = SimpleSQLiteQuery("DELETE FROM ${DatabaseService(entityClass).getTableName()}")
        return queryEliminarTodos(query)
    }

    @RawQuery
    protected abstract suspend fun queryBuscarTodos(query: SupportSQLiteQuery?): List<T>

    @RawQuery
    protected abstract suspend fun queryEliminarTodos(query: SupportSQLiteQuery?): Int
}

open class DatabaseService<T>(private val entityClass: Class<T>) {

    fun getTableName(): String {
        return when (entityClass) {
            entityClass::class.java -> "SomeTableThatDoesntMatchClassName"
            else -> entityClass.simpleName.lowercase(Locale.ROOT)
        }
    }
}