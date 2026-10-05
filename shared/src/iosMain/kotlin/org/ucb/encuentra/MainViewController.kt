package org.ucb.encuentra

import androidx.compose.ui.window.ComposeUIViewController
import org.ucb.encuentra.di.initKoinIos

// initKoinIos() se llama una sola vez, antes de crear el ComposeUIViewController
// (nunca dentro del lambda de contenido: si no, Koin se reinicia en cada
// recomposición y la app crashea con "A Koin Application has already been started").
private var koinInitialized = false

fun MainViewController() = run {
    if (!koinInitialized) {
        initKoinIos()
        koinInitialized = true
    }
    ComposeUIViewController { App() }
}
