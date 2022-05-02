package com.sevdotdev.statshotrebound.data.datasource.impl

import com.sevdotdev.statshotrebound.data.datasource.DataSource
import com.sevdotdev.statshotrebound.data.mappers.DEF_PLAYER_ID
import com.sevdotdev.statshotrebound.data.mappers.toDomain
import com.sevdotdev.statshotrebound.data.mappers.toEntity
import com.sevdotdev.statshotrebound.data.mappers.toInMatchEntity
import com.sevdotdev.statshotrebound.data.network.entities.MatchJson
import com.sevdotdev.statshotrebound.domain.model.Match
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tables.MatchEntityQueries
import tables.PlayerEntityQueries
import tables.PlayerInMatchEntityQueries
import tables.StatsEntityQueries
import javax.inject.Inject

class SqlDelightMatchDataSource @Inject constructor(
    private val matchEntityQueries: MatchEntityQueries,
    private val playerInMatchEntityQueries: PlayerInMatchEntityQueries,
    private val playerQueries: PlayerEntityQueries,
    private val statsEntityQueries: StatsEntityQueries
) : DataSource<MatchJson, Match> {

    override fun getAll(key: String?): Flow<List<Match>> =
        matchEntityQueries.getAllMatches().asFlow().mapToList()
            .map {
                it.map { matchEntity ->
                    matchEntity.toDomain()
                }
            }

    override suspend fun save(input: MatchJson) {
        val matchesFromNetwork = input.matches
        matchesFromNetwork?.let { matchesIn ->
            matchesIn.forEach { match ->
                matchEntityQueries.transaction {
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
                                statsEntityQueries.insertStats(
                                    it.toEntity(
                                        matchId,
                                        player.gameUserId ?: DEF_PLAYER_ID
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }


    override suspend fun get(key: String?): Match {
        TODO("Not yet implemented")
    }
}