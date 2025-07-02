package com.learn.kodes_help_app.features.auth.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.learn.kodes_help_app.features.auth.domain.AuthUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class AuthViewModel(
    private val useCases: AuthUseCase,
) : ViewModel() {
    var username = mutableStateOf("")
    var password = mutableStateOf("")
    var confirmPassword = mutableStateOf("")

    var error = mutableStateOf<String?>(null)
    var isLoading = mutableStateOf(false)

    var authSuccess = mutableStateOf(false)

    // coroutine scope for async functions
    private val viewModelScope = CoroutineScope(Dispatchers.Main)


    private fun validatePassword(): Boolean {
        when {
            password.value.isEmpty() -> {
                error.value = "Password cannot be empty"
                return false
            }
            password.value.length < 6 -> {
                error.value = "Password must be at least 6 characters long"
                return false
            }
            password.value != confirmPassword.value -> {
                error.value = "Passwords do not match"
                return false
            }
            else -> return true
        }
    }

     fun login() {
        isLoading.value = true
        error.value = null

        viewModelScope.launch {
            val result = useCases.login(username.value, password.value)
            isLoading.value = false
            result.onSuccess {
                authSuccess.value = true
            }.onFailure {
                error.value = it.message
            }
        }

    }

    fun register() {

        // validam functiile mai intai

        if (!validatePassword()) return

        isLoading.value = true
        error.value = null

        viewModelScope.launch {
            val result = useCases.register(username.value, password.value)
            isLoading.value = false

            result.onSuccess {
                authSuccess.value = true
            }.onFailure {
                error.value = it.message
            }
        }
    }

    fun resetAuthSuccess() {
        authSuccess.value = false
    }

    fun clearFields() {
        username.value = ""
        password.value = ""
        confirmPassword.value = ""
    }

    fun resetError() {
        error.value = null
    }
}