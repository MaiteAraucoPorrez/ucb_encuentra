package org.ucb.encuentra.feature.matchlist.presentation.viewmodel

sealed interface MatchListEffect {
    data class NavigateToDetail(val matchId: String) : MatchListEffect
    data class ShowMessage(val message: String) : MatchListEffect
}
