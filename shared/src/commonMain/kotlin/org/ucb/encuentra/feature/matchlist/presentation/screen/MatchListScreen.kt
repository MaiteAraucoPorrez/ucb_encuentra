package org.ucb.encuentra.feature.matchlist.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.viewmodel.koinViewModel
import org.ucb.encuentra.feature.matchlist.domain.model.MatchModel
import org.ucb.encuentra.feature.matchlist.presentation.viewmodel.MatchListEffect
import org.ucb.encuentra.feature.matchlist.presentation.viewmodel.MatchListIntent
import org.ucb.encuentra.feature.matchlist.presentation.viewmodel.MatchListViewModel

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MatchListScreen(
    onNavigateToDetail: (String) -> Unit,
    onNavigateToProfile: () -> Unit,
    viewModel: MatchListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is MatchListEffect.NavigateToDetail -> onNavigateToDetail(effect.matchId)
                is MatchListEffect.ShowMessage -> snackbarHostState.showSnackbar(effect.message)
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Posibles Coincidencias") },
                actions = {
                    androidx.compose.material3.TextButton(onClick = onNavigateToProfile) { Text("Perfil") }
                }
            )
        }
    ) { padding ->
        if (state.isLoading) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            return@Scaffold
        }

        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            Text(
                text = "Revisa estos objetos encontrados",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.outline,
                modifier = Modifier.padding(16.dp)
            )

            // FlowRow: los chips pasan a la línea siguiente si no caben (clase de FlowRow/FlowColumn)
            FlowRow(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = state.selectedCategory == null,
                    onClick = { viewModel.onIntent(MatchListIntent.OnCategorySelected(null)) },
                    label = { Text("Todos") }
                )
                state.categories.forEach { category ->
                    FilterChip(
                        selected = state.selectedCategory == category,
                        onClick = { viewModel.onIntent(MatchListIntent.OnCategorySelected(category)) },
                        label = { Text(category) }
                    )
                }
            }

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(state.matches, key = { it.id }) { match ->
                    MatchRow(match = match, onClick = { viewModel.onIntent(MatchListIntent.OnMatchClicked(match.id)) })
                }
            }

            OutlinedButton(
                onClick = { viewModel.onIntent(MatchListIntent.OnModifyCriteriaClicked) },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text("Modificar Criterios")
            }
        }
    }
}

@Composable
private fun MatchRow(match: MatchModel, onClick: () -> Unit) {
    val scoreColor = when {
        match.matchScore >= 80 -> Color(0xFF2E7D32) // verde
        match.matchScore >= 50 -> Color(0xFFEF6C00) // naranja
        else -> Color(0xFF757575)                   // gris
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(match.category.uppercase(), style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.outline)
                Text(match.itemTitle, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                Text("Encontrado: ${match.foundDate}", style = MaterialTheme.typography.bodySmall)
            }
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(scoreColor),
                contentAlignment = Alignment.Center
            ) {
                Text("${match.matchScore}%", color = Color.White, style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
            }
        }
    }
}
