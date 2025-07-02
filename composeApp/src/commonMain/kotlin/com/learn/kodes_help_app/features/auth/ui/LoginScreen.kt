package com.learn.kodes_help_app.features.auth.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.learn.kodes_help_app.features.auth.presentation.AuthViewModel

@Composable
fun LoginScreen(viewModel: AuthViewModel,
                onLoginSuccess: () -> Unit,
                onNavigateToRegister: () -> Unit
) {
    val username by viewModel.username
    val password by viewModel.password
    val isLoading by viewModel.isLoading
    val error by viewModel.error
    val authSuccess by viewModel.authSuccess


    LaunchedEffect(authSuccess) {
        if (authSuccess) {
            onLoginSuccess()
            viewModel.resetAuthSuccess()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // error message
        error?.let {
            Text(it, color = Color.Red)
            Spacer(Modifier.height(16.dp))
        }

        // username field
        OutlinedTextField(
            value = username,
            onValueChange = {
                viewModel.username.value = it
            },
            label = {Text("Username")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        // password field
        OutlinedTextField(
            value = password,
            onValueChange = {
                viewModel.password.value = it
            },
            label = {Text("Password")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))


        // login button
        Button(
            onClick = viewModel::login,
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLoading
        ) {
            if (isLoading) CircularProgressIndicator(Modifier.size(20.dp))
            else Text("Login")
        }

        Spacer(Modifier.height(16.dp))

        TextButton(
            onClick = onNavigateToRegister
        ) {
            Text("Don't have an account? Register")
        }

    }

}