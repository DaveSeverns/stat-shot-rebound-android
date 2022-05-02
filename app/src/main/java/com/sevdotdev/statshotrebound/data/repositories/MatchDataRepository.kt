package com.sevdotdev.statshotrebound.data.repositories


import com.sevdotdev.statshotrebound.StatsShotDataBase
import com.sevdotdev.statshotrebound.data.datasource.DataSource
import com.sevdotdev.statshotrebound.data.mappers.DEF_PLAYER_ID
import com.sevdotdev.statshotrebound.data.mappers.toEntity
import com.sevdotdev.statshotrebound.data.mappers.toInMatchEntity
import com.sevdotdev.statshotrebound.data.network.MatchService
import com.sevdotdev.statshotrebound.data.network.entities.MatchJson
import com.sevdotdev.statshotrebound.domain.DataRepository
import com.sevdotdev.statshotrebound.domain.model.Match
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MatchDataRepository @Inject constructor(
    private val matchService: MatchService,
    private val matchDataSource: DataSource<MatchJson, Match>
) : DataRepository<Match> {

    override suspend fun get(getKey: String?): Match {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(getKey: String?): Flow<List<Match>> {
        return matchDataSource.getAll()
    }

    override suspend fun save(dataItem: Match) {
        TODO("Not yet implemented")
    }

    override suspend fun saveAll(dataItems: List<Match>) {
        TODO("Not yet implemented")
    }

    override suspend fun retrieveFromServer() {
        val matchJson = matchService.getAllMatches()
        matchDataSource.save(matchJson)
    }

}