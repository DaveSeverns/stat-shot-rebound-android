package com.sevdotdev.statshotrebound.di

import com.sevdotdev.statshotrebound.data.datasource.DataSource
import com.sevdotdev.statshotrebound.data.datasource.impl.SqlDelightMatchDataSource
import com.sevdotdev.statshotrebound.data.network.entities.MatchJson
import com.sevdotdev.statshotrebound.data.repositories.MatchDataRepository
import com.sevdotdev.statshotrebound.domain.DataRepository
import com.sevdotdev.statshotrebound.domain.model.Match
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun matchDataRepository(matchDataRepository: MatchDataRepository): DataRepository<Match> =
        matchDataRepository

    @Provides
    fun matchDataSource(dataSource: SqlDelightMatchDataSource): DataSource<MatchJson, Match> = dataSource
}