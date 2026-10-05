package org.ucb.encuentra.feature.matchlist.domain.usecase

import org.ucb.encuentra.feature.matchlist.domain.model.MatchModel

/** Lógica pura (sin repositorio): `category = null` significa "Todos". */
class FilterMatchesByCategoryUseCase {
    operator fun invoke(matches: List<MatchModel>, category: String?): List<MatchModel> =
        if (category == null) matches else matches.filter { it.category == category }
}
