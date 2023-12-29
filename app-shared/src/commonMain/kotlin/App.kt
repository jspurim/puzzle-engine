package com.github.jspurim.puzzleengine.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.github.jspurim.csp.model.libraryFunction

@Composable
fun App() {
    var text by remember { mutableStateOf("Hello, World!") }
    val platformName = getPlatformName()
    val libraryValue = libraryFunction()

    Column {
        BasicText(libraryValue)
        Button(onClick = {
            text = "Hello, ${platformName}"
        }) {
            Text(text)
        }
    }
}