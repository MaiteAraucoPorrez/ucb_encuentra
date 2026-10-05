package org.ucb.encuentra.feature.matchlist.data.local

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MatchDao {
    @Query("SELECT * FROM matches WHERE status = 'PENDING' ORDER BY matchScore DESC")
    fun observePending(): Flow<List<MatchEntity>>

    @Query("SELECT * FROM matches WHERE id = :id LIMIT 1")
    suspend fun findById(id: String): MatchEntity?

    @Query("SELECT COUNT(*) FROM matches WHERE status = 'PENDING'")
    suspend fun countPending(): Int

    @Query("SELECT COUNT(*) FROM matches")
    suspend fun count(): Int

    @Insert
    suspend fun insertAll(matches: List<MatchEntity>)

    @Query("UPDATE matches SET status = :status WHERE id = :id")
    suspend fun updateStatus(id: String, status: String)
}
