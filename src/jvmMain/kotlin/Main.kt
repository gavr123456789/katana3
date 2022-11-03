// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.ScrollbarStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import java.nio.file.Path


@Composable
fun Greeting() {
    Text(text = "Sas!!!", style = TextStyle(color = Color.Red))
}

@Composable
@Preview
fun App() {
    val maybeFiles = getFiles(".")

    if (maybeFiles.isNullOrEmpty()) {
        return
    }
    var files: List<Path> by rememberSaveable() { mutableStateOf(maybeFiles) }

    println(files)

    MaterialTheme {
        CompositionLocalProvider(
            LocalScrollbarStyle provides ScrollbarStyle(
                minimalHeight = 16.dp,
                thickness = 8.dp,
                shape = MaterialTheme.shapes.small,
                hoverDurationMillis = 300,
                unhoverColor = MaterialTheme.colors.onSurface.copy(alpha = 0.12f),
                hoverColor = MaterialTheme.colors.onSurface.copy(alpha = 0.50f),
            )
        ) {
//            LazyScrollable(files = files, onFilesChange = { files = it })
//            LayoutTest2()
            ScaffoldExample()
        }
    }

}

fun main() = application {

    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
