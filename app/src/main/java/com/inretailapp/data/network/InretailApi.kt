package com.inretailapp.data.network

import com.inretailapp.data.model.http.AuthResponse
import com.inretailapp.data.model.http.LoginRequest
import retrofit2.http.Body
import retrofit2.http.POST

const val API_VERSION = "/api"

interface InretailApi {
    @POST("$API_VERSION/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): AuthResponse
}