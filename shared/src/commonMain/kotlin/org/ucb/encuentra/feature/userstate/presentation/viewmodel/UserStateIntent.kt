package org.ucb.encuentra.feature.userstate.presentation.viewmodel

sealed interface UserStateIntent {
    data object LoadProfile : UserStateIntent
    data object OnLogoutClicked : UserStateIntent
    data object OnSearchGithubClicked : UserStateIntent
}
