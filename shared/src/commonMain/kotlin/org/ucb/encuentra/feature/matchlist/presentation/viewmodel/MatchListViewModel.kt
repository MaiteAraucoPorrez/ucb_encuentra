package org.ucb.encuentra.feature.matchlist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ucb.encuentra.feature.matchlist.domain.model.MatchModel
import org.ucb.encuentra.feature.matchlist.domain.usecase.FilterMatchesByCategoryUseCase
import org.ucb.encuentra.feature.matchlist.domain.usecase.ObserveMatchesUseCase

class MatchListViewModel(
    private val observeMatches: ObserveMatchesUseCase,
    private val filterMatchesByCategory: FilterMatchesByCategoryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MatchListState(isLoading = true))
    val state: StateFlow<MatchListState> = _state.asStateFlow()

    private val _effect = Channel<MatchListEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    init {
        observe()
    }

    fun onIntent(intent: MatchListIntent) {
        when (intent) {
            is MatchListIntent.OnCategorySelected -> selectCategory(intent.category)
            is MatchListIntent.OnMatchClicked -> sendEffect(MatchListEffect.NavigateToDetail(intent.matchId))
            MatchListIntent.OnModifyCriteriaClicked -> sendEffect(MatchListEffect.ShowMessage("Función en desarrollo"))
        }
    }

    // Room vuelve a emitir cuando se reclama/rechaza un match: la lista se actualiza sola.
    private fun observe() {
        viewModelScope.launch {
            observeMatches()
                .catch { error ->
                    _state.update {
                        it.copy(isLoading = false, errorMessage = error.message ?: "No se pudieron cargar los matches")
                    }
                }
                .collect { matches -> onMatchesChanged(matches) }
        }
    }

    private fun onMatchesChanged(matches: List<MatchModel>) {
        _state.update { current ->
            // si ya no quedan matches de la categoría elegida, vuelve a "Todos"
            val category = current.selectedCategory?.takeIf { c -> matches.any { it.category == c } }
            current.copy(
                isLoading = false,
                errorMessage = null,
                allMatches = matches,
                selectedCategory = category,
                matches = filterMatchesByCategory(matches, category)
            )
        }
    }

    private fun selectCategory(category: String?) {
        _state.update { current ->
            current.copy(
                selectedCategory = category,
                matches = filterMatchesByCategory(current.allMatches, category)
            )
        }
    }

    private fun sendEffect(effect: MatchListEffect) {
        viewModelScope.launch { _effect.send(effect) }
    }
}
