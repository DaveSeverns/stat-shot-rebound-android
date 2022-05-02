package com.sevdotdev.statshotrebound.data.datasource

import com.sevdotdev.statshotrebound.domain.model.Match
import kotlinx.coroutines.flow.Flow
import tables.MatchEntity


interface MatchDataSource {
    fun getAllMatches(): Flow<List<Match>>
}