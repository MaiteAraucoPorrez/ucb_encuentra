package org.ucb.encuentra.feature.login.presentation.viewmodel

sealed interface LoginEffect {
    data object NavigateToHome : LoginEffect
    data class ShowError(val message: String) : LoginEffect
}
