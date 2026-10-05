package org.ucb.encuentra.feature.matchdetail.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import org.ucb.encuentra.feature.matchdetail.presentation.viewmodel.MatchDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchDetailScreen(
    matchId: String,
    onNavigateBack: () -> Unit,
    viewModel: MatchDetailViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(matchId) {
        viewModel.loadMatch(matchId)
    }

    LaunchedEffect(state.actionMessage) {
        state.actionMessage?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.onActionMessageShown()
            onNavigateBack()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Detalle de Hallazgo") },
                navigationIcon = { TextButton(onClick = onNavigateBack) { Text("← Volver") } }
            )
        }
    ) { padding ->
        when {
            state.isLoading -> Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }

            state.errorMessage != null -> Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text(state.errorMessage.orEmpty())
            }

            state.match != null -> {
                val match = state.match!!
                Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
                    Text(match.itemTitle, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Text("${match.matchScore}% de coincidencia · ${match.category}", style = MaterialTheme.typography.bodyMedium)

                    Spacer(Modifier.height(16.dp))
                    Text("Análisis de coincidencia", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(8.dp))

                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(Modifier.padding(16.dp)) {
                            ComparisonRow("Marca", match.reportedBrand, match.foundBrand)
                            Spacer(Modifier.height(8.dp))
                            ComparisonRow("Color", match.reportedColor, match.foundColor)
                            Spacer(Modifier.height(8.dp))
                            ComparisonRow("Ubicación", match.reportedLocation, match.foundLocation)
                        }
                    }

                    Spacer(Modifier.height(16.dp))
                    Text("Nota del hallazgo", style = MaterialTheme.typography.titleMedium)
                    Spacer(Modifier.height(4.dp))
                    Text(match.foundNote, style = MaterialTheme.typography.bodyMedium)

                    Spacer(Modifier.height(24.dp))
                    Button(
                        onClick = { viewModel.onClaimClicked() },
                        modifier = Modifier.fillMaxWidth().height(50.dp)
                    ) {
                        Icon(Icons.Default.CheckCircle, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Esto es mío — Verificar")
                    }

                    Spacer(Modifier.height(8.dp))
                    OutlinedButton(
                        onClick = { viewModel.onRejectClicked() },
                        modifier = Modifier.fillMaxWidth().height(50.dp)
                    ) {
                        Icon(Icons.Default.Close, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("No es lo mío, seguir buscando")
                    }
                }
            }
        }
    }
}

@Composable
private fun ComparisonRow(label: String, reported: String, found: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(end = 16.dp)) {
            Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.outline)
            Text("Reportaste: $reported", style = MaterialTheme.typography.bodySmall)
            Text("Se encontró: $found", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
        }
    }
}
