package com.sevdotdev.statshotrebound.data.datasource.impl

import com.sevdotdev.statshotrebound.data.datasource.MatchDataSource
import com.sevdotdev.statshotrebound.data.mappers.toDomain
import com.sevdotdev.statshotrebound.domain.model.Match
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import tables.MatchEntityQueries
import javax.inject.Inject

class SqlDelightMatchDataSource @Inject constructor(
    val matchEntityQueries: MatchEntityQueries
) : MatchDataSource {
    override fun getAllMatches(): Flow<List<Match>> =
        matchEntityQueries.getAllMatches().asFlow().mapToList()
            .map {
                it.map { matchEntity ->
                    matchEntity.toDomain()
                }
            }
}