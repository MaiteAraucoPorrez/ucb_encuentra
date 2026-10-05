package org.ucb.encuentra.feature.matchdetail.presentation.viewmodel

import org.ucb.encuentra.feature.matchlist.domain.model.MatchModel

data class MatchDetailState(
    val isLoading: Boolean = false,
    val match: MatchModel? = null,
    val errorMessage: String? = null,
    val actionMessage: String? = null
)
