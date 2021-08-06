package com.inretailapp.data.model.http

data class LoginRequest(val correo: String, val password: String)

data class AuthResponse (
    val usuario: Usuario,
    val token: String?
)

data class Usuario (
    val rol: String,
    val estado: Boolean,
    val google: Boolean,
    val nombre: String,
    val correo: String,
    val uid: String
)