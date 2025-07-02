package com.learn.kodes_help_app.features.auth.domain

import com.learn.kodes_help_app.features.auth.data.AuthRepository

class AuthUseCase(private val repository: AuthRepository) {
    suspend fun login(username: String, password:String) =
        repository.login(username, password)
    suspend fun register(username: String, password: String) =
        repository.register(username,password)
}