package org.ucb.encuentra.feature.matchlist.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.ucb.encuentra.feature.matchlist.domain.model.MatchModel
import org.ucb.encuentra.feature.matchlist.domain.repository.MatchRepository

class ObserveMatchesUseCase(private val repository: MatchRepository) {
    operator fun invoke(): Flow<List<MatchModel>> = repository.observeMatches()
}
