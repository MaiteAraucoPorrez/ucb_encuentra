package org.ucb.encuentra.feature.matchdetail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ucb.encuentra.feature.matchdetail.domain.usecase.ClaimMatchUseCase
import org.ucb.encuentra.feature.matchdetail.domain.usecase.GetMatchDetailUseCase
import org.ucb.encuentra.feature.matchdetail.domain.usecase.RejectMatchUseCase

/**
 * MVVM (sin Intent/Effect), igual convención que Movie Detail y Sign Up.
 */
class MatchDetailViewModel(
    private val getMatchDetail: GetMatchDetailUseCase,
    private val claimMatch: ClaimMatchUseCase,
    private val rejectMatch: RejectMatchUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MatchDetailState())
    val state: StateFlow<MatchDetailState> = _state.asStateFlow()

    fun loadMatch(matchId: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, errorMessage = null) }
            val match = getMatchDetail(matchId)
            if (match != null) {
                _state.update { it.copy(isLoading = false, match = match) }
            } else {
                _state.update { it.copy(isLoading = false, errorMessage = "Coincidencia no encontrada") }
            }
        }
    }

    fun onClaimClicked() {
        val matchId = _state.value.match?.id ?: return
        viewModelScope.launch {
            claimMatch(matchId)
            _state.update { it.copy(actionMessage = "Reclamo enviado para verificación") }
        }
    }

    fun onRejectClicked() {
        val matchId = _state.value.match?.id ?: return
        viewModelScope.launch {
            rejectMatch(matchId)
            _state.update { it.copy(actionMessage = "Seguimos buscando tu objeto") }
        }
    }

    fun onActionMessageShown() {
        _state.update { it.copy(actionMessage = null) }
    }
}
