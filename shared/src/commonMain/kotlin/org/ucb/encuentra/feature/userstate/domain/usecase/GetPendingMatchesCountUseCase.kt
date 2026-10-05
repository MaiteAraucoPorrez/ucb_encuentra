package org.ucb.encuentra.feature.userstate.domain.usecase

import org.ucb.encuentra.feature.matchlist.domain.repository.MatchRepository

class GetPendingMatchesCountUseCase(private val repository: MatchRepository) {
    suspend operator fun invoke(): Int = repository.getPendingCount()
}
