package com.sevdotdev.statshotrebound.data.repositories


import com.sevdotdev.statshotrebound.StatsShotDataBase
import com.sevdotdev.statshotrebound.data.datasource.MatchDataSource
import com.sevdotdev.statshotrebound.data.mappers.DEF_PLAYER_ID
import com.sevdotdev.statshotrebound.data.mappers.toEntity
import com.sevdotdev.statshotrebound.data.mappers.toInMatchEntity
import com.sevdotdev.statshotrebound.data.network.MatchService
import com.sevdotdev.statshotrebound.domain.DataRepository
import com.sevdotdev.statshotrebound.domain.model.Match
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MatchDataRepository @Inject constructor(
    private val matchService: MatchService,
    private val statsShotDataBase: StatsShotDataBase,
    private val matchDataSource: MatchDataSource
): DataRepository<Match> {

    val matchEntityQueries = statsShotDataBase.matchEntityQueries
    val playerInMatchEntityQueries = statsShotDataBase.playerInMatchEntityQueries
    val playerQueries = statsShotDataBase.playerEntityQueries
    val statsEntityQueries = statsShotDataBase.statsEntityQueries

    override suspend fun get(getKey: String?): Match {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(getKey: String?): Flow<List<Match>> {
        withContext(Dispatchers.IO) {
            val matchesFromNetwork = matchService.getAllMatches().matches ?: emptyList()
            if (matchesFromNetwork.isNotEmpty()) {
                matchesFromNetwork.forEach { match ->
                    statsShotDataBase.transaction {
                        matchEntityQueries.insertMatch(match.toEntity())
                        val id = matchEntityQueries.selectLastInsertedRowId().executeAsOneOrNull()
                        id?.let { matchId ->
                            match.players?.forEach { player ->
                                playerInMatchEntityQueries.insertPlayerInMatch(
                                    player.toInMatchEntity(matchId = matchId)
                                )
                                playerQueries.insertPlayer(
                                    player.toEntity()
                                )
                                player.stats?.let {
                                    statsEntityQueries.insertStats(it.toEntity(matchId, player.gameUserId ?: DEF_PLAYER_ID))
                                }
                            }
                        }
                    }
                }
            }
        }
        return matchDataSource.getAllMatches()
    }

    override suspend fun save(dataItem: Match) {
        TODO("Not yet implemented")
    }

    override suspend fun saveAll(dataItems: List<Match>) {
        TODO("Not yet implemented")
    }

}