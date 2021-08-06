package com.inretailapp.app.providers

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun provideDataStoreInstance(applicationContext: Context): DataStore<Preferences> {
    return applicationContext.createDataStore(
        name = "settings"
    )
}

class DataStoreProvider(private val dataStore: DataStore<Preferences>) {

    val accessToken: Flow<String>
        get() = dataStore.data.map { preferences ->
            preferences[ACCESS_TOKEN]?: ""
        }


    suspend fun grabarTokenDeAcceso(accessToken: String) {
        dataStore.addToLocalStorage {
            this[ACCESS_TOKEN] = accessToken
        }
    }

    suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("access_token")
    }

}

suspend fun DataStore<Preferences>.addToLocalStorage(mutableFunc: MutablePreferences.() -> Unit) {
    edit {
        mutableFunc(it)
    }
}

