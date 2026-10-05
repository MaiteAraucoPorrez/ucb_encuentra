package org.ucb.encuentra.navigation

import kotlinx.serialization.Serializable

sealed interface NavRoute {
    @Serializable data object Login : NavRoute
    @Serializable data object SignUp : NavRoute
    @Serializable data object MatchList : NavRoute
    @Serializable data class MatchDetail(val matchId: String) : NavRoute
    @Serializable data object Profile : NavRoute
    @Serializable data object UserSearch : NavRoute
}
