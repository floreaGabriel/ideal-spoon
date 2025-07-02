package com.learn.kodes_help_app.features.auth.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    onNavigateToLogin: () -> Unit
) {
    Column {
        Text("HOME SCREEN")

        Button(
            onClick = onNavigateToLogin
        ) {
            Text("Back to login")

        }
    }
}