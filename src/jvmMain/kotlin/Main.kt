// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.LocalScrollbarStyle
import androidx.compose.foundation.ScrollbarStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    val maybeFiles = getFiles(".")

    if (maybeFiles.isNullOrEmpty()) {
        return
    }
//    var files: List<Path> by rememberSaveable() { mutableStateOf(maybeFiles) }
//
//    println(files)

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
            MainLayout()
        }
    }

}

fun main() = application {

    Window(
        onCloseRequest = ::exitApplication, state = WindowState(
            height = 400.dp, width = 300.dp,
            position = WindowPosition.Aligned(
                Alignment.Center
            )
        )
    ) {
        App()
    }
}
