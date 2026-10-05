package org.ucb.encuentra

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.ucb.encuentra.navigation.AppNavHost

@Composable
fun App() {
    MaterialTheme {
        AppNavHost()
    }
}
