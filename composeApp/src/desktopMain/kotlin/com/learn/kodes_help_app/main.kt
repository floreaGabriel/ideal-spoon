package com.learn.kodes_help_app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "kodes_help_app",
    ) {
        App()
    }
}