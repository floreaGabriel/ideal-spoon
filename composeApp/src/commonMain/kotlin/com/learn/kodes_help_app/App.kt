package com.learn.kodes_help_app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.learn.kodes_help_app.features.auth.data.DummyAuthRepo
import com.learn.kodes_help_app.features.auth.domain.AuthUseCase
import com.learn.kodes_help_app.features.auth.presentation.AuthViewModel
import com.learn.kodes_help_app.features.auth.ui.HomeScreen
import com.learn.kodes_help_app.features.auth.ui.LoginScreen
import com.learn.kodes_help_app.features.auth.ui.RegisterScreen
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import kodes_help_app.composeapp.generated.resources.Res
import kodes_help_app.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {

        // initializare repo,use case, view model

        val authRepository = remember { DummyAuthRepo() }
        val authUseCase = remember { AuthUseCase(authRepository) }
        val authViewModel = remember { AuthViewModel(authUseCase) }

        
        var currentScreen by remember { mutableStateOf(Screen.LOGIN) }


        when (currentScreen) {
            Screen.LOGIN -> {
                LaunchedEffect(currentScreen) {
                    if (currentScreen == Screen.LOGIN) {
                        authViewModel.resetAuthSuccess()
                        authViewModel.clearFields()
                        authViewModel.resetError()
                    }
                }
                LoginScreen(
                    authViewModel,
                    onLoginSuccess = {currentScreen = Screen.HOME},
                    onNavigateToRegister = {currentScreen = Screen.REGISTER}
                )
            }
            Screen.REGISTER -> {
                LaunchedEffect(currentScreen) {
                    if (currentScreen == Screen.REGISTER) {
                        authViewModel.resetAuthSuccess()
                        authViewModel.clearFields()
                        authViewModel.resetError()
                    }
                }
                RegisterScreen(
                    authViewModel,
                    onRegisterSuccess = {currentScreen = Screen.HOME},
                    onNavigateToLogin = {currentScreen = Screen.LOGIN}
                )
            }
            Screen.HOME -> HomeScreen(
                onNavigateToLogin = {currentScreen = Screen.LOGIN}
            )
        }

    }
}


enum class Screen {LOGIN, REGISTER, HOME}