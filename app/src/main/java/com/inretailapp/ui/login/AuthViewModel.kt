package com.inretailapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inretailapp.data.model.Configuraciones
import com.inretailapp.data.model.http.AuthResponse
import com.inretailapp.data.network.Resource
import com.inretailapp.data.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
): ViewModel() {

    private val _authResponse: MutableLiveData<Resource<AuthResponse>> = MutableLiveData()
    val authResponse: LiveData<Resource<AuthResponse>>
        get() = _authResponse

    fun login(nombreUsuario: String, passwordUsuario: String) = viewModelScope.launch {
        _authResponse.value = Resource.Loading
        _authResponse.value = repository.login(nombreUsuario, passwordUsuario)
    }

    fun grabarTokenDeAcceso(accessToken: String) = viewModelScope.launch {
        repository.grabarTokenDeAcceso(accessToken)
    }
}