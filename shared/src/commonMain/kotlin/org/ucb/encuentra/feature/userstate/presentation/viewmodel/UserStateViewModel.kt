package org.ucb.encuentra.feature.userstate.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.ucb.encuentra.feature.userstate.domain.usecase.GetCurrentUserUseCase
import org.ucb.encuentra.feature.userstate.domain.usecase.GetPendingMatchesCountUseCase
import org.ucb.encuentra.feature.userstate.domain.usecase.LogoutUseCase

class UserStateViewModel(
    private val getCurrentUser: GetCurrentUserUseCase,
    private val getPendingMatchesCount: GetPendingMatchesCountUseCase,
    private val logout: LogoutUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(UserStateState())
    val state: StateFlow<UserStateState> = _state.asStateFlow()

    private val _effect = Channel<UserStateEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    init {
        onIntent(UserStateIntent.LoadProfile)
    }

    fun onIntent(intent: UserStateIntent) {
        when (intent) {
            UserStateIntent.LoadProfile -> loadProfile()
            UserStateIntent.OnLogoutClicked -> onLogoutClicked()
            UserStateIntent.OnSearchGithubClicked -> sendEffect(UserStateEffect.NavigateToUserSearch)
        }
    }

    private fun loadProfile() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val user = getCurrentUser()
            val pending = getPendingMatchesCount()
            _state.update { it.copy(isLoading = false, user = user, pendingMatchesCount = pending) }
        }
    }

    private fun onLogoutClicked() {
        logout()
        sendEffect(UserStateEffect.NavigateToLogin)
    }

    private fun sendEffect(effect: UserStateEffect) {
        viewModelScope.launch { _effect.send(effect) }
    }
}
