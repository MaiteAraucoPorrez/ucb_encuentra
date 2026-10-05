package org.ucb.encuentra.feature.login.presentation.viewmodel

sealed interface LoginIntent {
    data class OnEmailChanged(val value: String) : LoginIntent
    data class OnPasswordChanged(val value: String) : LoginIntent
    data object OnTogglePasswordVisibility : LoginIntent
    data object OnSubmit : LoginIntent
}
