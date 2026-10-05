package org.ucb.encuentra.feature.userstate.presentation.viewmodel

sealed interface UserStateEffect {
    data object NavigateToLogin : UserStateEffect
    data object NavigateToUserSearch : UserStateEffect
}
