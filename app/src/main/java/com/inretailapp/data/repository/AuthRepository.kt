package com.inretailapp.data.repository

import com.inretailapp.app.providers.DataStoreProvider
import com.inretailapp.data.database.LocalDataSource
import com.inretailapp.data.model.http.LoginRequest
import com.inretailapp.data.network.InretailApi
import com.inretailapp.data.network.SafeApiCall

class AuthRepository(
    private val dataStore: DataStoreProvider,
    private val api: InretailApi,
    private val dao: LocalDataSource
): SafeApiCall {

    suspend fun login(
        nombreUsuario   : String,
        passwordUsuario : String
    ) = safeApiCall {
        api.login(LoginRequest(nombreUsuario, passwordUsuario))
    }

    suspend fun grabarTokenDeAcceso(
        accessToken : String,
    ) {
        dataStore.grabarTokenDeAcceso(accessToken)
    }
}

