package com.qamar.jetpackcomposscanner

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "JetpackComposScanner",
    ) {
        RecompositionTrackerScreen()
    }
}
