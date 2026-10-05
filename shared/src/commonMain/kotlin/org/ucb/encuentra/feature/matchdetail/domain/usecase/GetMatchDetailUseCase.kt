package org.ucb.encuentra.feature.matchdetail.domain.usecase

import org.ucb.encuentra.feature.matchlist.domain.model.MatchModel
import org.ucb.encuentra.feature.matchlist.domain.repository.MatchRepository

class GetMatchDetailUseCase(private val repository: MatchRepository) {
    suspend operator fun invoke(matchId: String): MatchModel? = repository.getMatchById(matchId)
}
