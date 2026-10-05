package org.ucb.encuentra.userinformation.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.ucb.encuentra.userinformation.domain.usecase.FindGithubUserUseCase

class UserInformationViewModel(
    private val findGithubUser: FindGithubUserUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UserInformationUiState>(UserInformationUiState.Idle)
    val uiState: StateFlow<UserInformationUiState> = _uiState.asStateFlow()

    fun fetchUser(username: String) {
        if (username.isBlank()) return
        viewModelScope.launch {
            _uiState.value = UserInformationUiState.Loading
            findGithubUser(username)
                .onSuccess { _uiState.value = UserInformationUiState.Success(it) }
                .onFailure { _uiState.value = UserInformationUiState.Error(it.message ?: "Error desconocido") }
        }
    }
}
