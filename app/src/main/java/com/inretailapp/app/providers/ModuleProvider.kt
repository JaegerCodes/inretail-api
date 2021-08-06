package com.inretailapp.app.providers

import com.inretailapp.data.database.LocalDataSource
import com.inretailapp.data.database.RoomDataSource
import com.inretailapp.data.repository.AuthRepository
import com.inretailapp.ui.login.AuthViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.binds
import org.koin.dsl.module

val remoteDataSourceModule = module {
    single { provideRemoteDataSource(get()) }
}

val dataStoreModule = module {
    single { provideDataStoreInstance(androidContext()) }
    single { DataStoreProvider(get()) }
}

val databaseModule = module {
    single { provideDatabase(androidApplication()) }
    single { provideConfiguracionDao(get()) }
    single { RoomDataSource(get()) } binds arrayOf(LocalDataSource::class)
}

val repositoriesModule = module {
    single { AuthRepository(get(), get(), get()) }
}

val viewModelsModule = module {
    single { AuthViewModel(get()) }
}
