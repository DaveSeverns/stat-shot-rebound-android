package com.sevdotdev.statshotrebound.data.di

import android.app.Application
import com.sevdotdev.statshotrebound.StatsShotDataBase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tables.MatchEntityQueries
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


    @Provides
    @Singleton
    fun providesDatabase(driver: SqlDriver): StatsShotDataBase = StatsShotDataBase(driver = driver)

    @Provides
    fun matchEntityQueries(dataBase: StatsShotDataBase): MatchEntityQueries = dataBase.matchEntityQueries
}

private const val DB_NAME = "statsshot.db"