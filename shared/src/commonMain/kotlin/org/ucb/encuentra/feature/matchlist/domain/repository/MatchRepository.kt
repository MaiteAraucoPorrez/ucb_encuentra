package org.ucb.encuentra.feature.matchlist.domain.repository

import kotlinx.coroutines.flow.Flow
import org.ucb.encuentra.feature.matchlist.domain.model.MatchModel

interface MatchRepository {
    /** Matches PENDING; emite de nuevo cada vez que cambia la tabla (claim/reject). */
    fun observeMatches(): Flow<List<MatchModel>>
    suspend fun getMatchById(matchId: String): MatchModel?
    suspend fun getPendingCount(): Int
    suspend fun claimMatch(matchId: String)
    suspend fun rejectMatch(matchId: String)
}
