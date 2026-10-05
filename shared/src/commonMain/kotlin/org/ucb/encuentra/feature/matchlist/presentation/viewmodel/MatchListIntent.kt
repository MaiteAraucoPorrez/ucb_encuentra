package org.ucb.encuentra.feature.matchlist.presentation.viewmodel

sealed interface MatchListIntent {
    data class OnCategorySelected(val category: String?) : MatchListIntent
    data class OnMatchClicked(val matchId: String) : MatchListIntent
    data object OnModifyCriteriaClicked : MatchListIntent
}
