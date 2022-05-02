package com.sevdotdev.statshotrebound.data.di

import android.app.Application
import com.sevdotdev.statshotrebound.StatsShotDataBase
import com.squareup.sqldelight.ColumnAdapter
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tables.MatchEntity
import tables.MatchEntityQueries
import tables.PlayerEntityQueries
import tables.PlayerInMatchEntityQueries
import tables.StatsEntityQueries
import java.util.Date
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideAndroidSqlDriver(app: Application): SqlDriver = AndroidSqliteDriver(
        schema = StatsShotDataBase.Schema,
        context = app,
        name = DB_NAME
    )

    private val dateColumnAdapter = object : ColumnAdapter<Date, Long> {
        override fun decode(databaseValue: Long): Date {
            return Date(databaseValue)
        }

        override fun encode(value: Date): Long {
            return value.time
        }

    }

    @Provides
    @Singleton
    fun providesDatabase(driver: SqlDriver): StatsShotDataBase = StatsShotDataBase(
        driver = driver,
        matchEntityAdapter = MatchEntity.Adapter(
            date_playedAdapter = dateColumnAdapter
        )
    )

    @Provides
    fun matchEntityQueries(dataBase: StatsShotDataBase): MatchEntityQueries = dataBase.matchEntityQueries

    @Provides
    fun playerInMatchQueries(dataBase: StatsShotDataBase): PlayerInMatchEntityQueries = dataBase.playerInMatchEntityQueries

    @Provides
    fun playerEntityQueries(dataBase: StatsShotDataBase): PlayerEntityQueries = dataBase.playerEntityQueries

    @Provides
    fun statsEntityQueries(dataBase: StatsShotDataBase): StatsEntityQueries = dataBase.statsEntityQueries

}

private const val DB_NAME = "statsshot.db"
