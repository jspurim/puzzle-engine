package com.github.jspurim.puzzleengine.app.desktop

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.github.jspurim.puzzleengine.app.App


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
