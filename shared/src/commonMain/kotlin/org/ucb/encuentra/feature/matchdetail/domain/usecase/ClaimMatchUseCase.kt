package org.ucb.encuentra.feature.matchdetail.domain.usecase

import org.ucb.encuentra.feature.matchlist.domain.repository.MatchRepository

class ClaimMatchUseCase(private val repository: MatchRepository) {
    suspend operator fun invoke(matchId: String) = repository.claimMatch(matchId)
}
