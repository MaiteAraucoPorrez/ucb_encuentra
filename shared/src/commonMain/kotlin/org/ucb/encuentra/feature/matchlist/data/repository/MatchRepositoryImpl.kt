package org.ucb.encuentra.feature.matchlist.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.ucb.encuentra.feature.matchlist.data.local.MatchDao
import org.ucb.encuentra.feature.matchlist.data.mapper.toDomain
import org.ucb.encuentra.feature.matchlist.data.seed.matchSeed
import org.ucb.encuentra.feature.matchlist.domain.model.MatchModel
import org.ucb.encuentra.feature.matchlist.domain.model.MatchStatus
import org.ucb.encuentra.feature.matchlist.domain.repository.MatchRepository

/** Persistido en Room (aún sin backend/IA real: los matches siguen siendo datos semilla). */
class MatchRepositoryImpl(private val dao: MatchDao) : MatchRepository {

    private val seedMutex = Mutex()
    private var seeded = false

    private suspend fun ensureSeeded() = seedMutex.withLock {
        if (!seeded) {
            if (dao.count() == 0) dao.insertAll(matchSeed)
            seeded = true
        }
    }

    override fun observeMatches(): Flow<List<MatchModel>> = flow {
        ensureSeeded()
        emitAll(dao.observePending().map { list -> list.map { it.toDomain() } })
    }

    override suspend fun getMatchById(matchId: String): MatchModel? {
        ensureSeeded()
        return dao.findById(matchId)?.toDomain()
    }

    override suspend fun getPendingCount(): Int {
        ensureSeeded()
        return dao.countPending()
    }

    override suspend fun claimMatch(matchId: String) = dao.updateStatus(matchId, MatchStatus.CLAIMED.name)

    override suspend fun rejectMatch(matchId: String) = dao.updateStatus(matchId, MatchStatus.REJECTED.name)
}
