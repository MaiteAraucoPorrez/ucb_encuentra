package org.ucb.encuentra.feature.matchlist.presentation.viewmodel

import org.ucb.encuentra.feature.matchlist.domain.model.MatchModel

data class MatchListState(
    val isLoading: Boolean = false,
    val allMatches: List<MatchModel> = emptyList(),
    val selectedCategory: String? = null, // null = "Todos"
    val matches: List<MatchModel> = emptyList(), // ya filtrados por categoría
    val errorMessage: String? = null
) {
    val categories: List<String> get() = allMatches.map { it.category }.distinct()
}
