package com.sevdotdev.statshotrebound.data.mappers

import android.annotation.SuppressLint
import com.sevdotdev.statshotrebound.data.network.entities.Match
import com.sevdotdev.statshotrebound.domain.model.MatchMetaData
import com.sevdotdev.statshotrebound.domain.model.TeamInMatch
import tables.MatchEntity
import java.text.SimpleDateFormat
import java.util.*

typealias DomainMatch = com.sevdotdev.statshotrebound.domain.model.Match
internal fun Match.toEntity(): MatchEntity {
    return MatchEntity(
        id = this.hashCode().toString(),
        arena = arena ?: "",
        away_score = score?.away?.toLongDefault(),
        home_score = score?.home?.toLongDefault(),
        periods_enabled = periodsEnabled?.contains("false"),
        current_period = currentPeriod ?: "none",
        custom_mercy_rule = customMercyRule,
        type = type,
        date_played = Date(System.currentTimeMillis())
    )
}

@SuppressLint("SimpleDateFormat")
internal fun MatchEntity.toDomain(): DomainMatch =
    DomainMatch(
        metaData = MatchMetaData(
            arena = arena,
            periodsEnable = periods_enabled ?: false,
            customMercyRule = custom_mercy_rule
        ),
        homeTeamData = TeamInMatch.Home(score = home_score?.toInt() ?: 0),
        awayTeamData = TeamInMatch.Away(score = away_score?.toInt() ?: 0),
        datePlayed = SimpleDateFormat("MM/dd/yyyy").format(date_played)
    )

fun Int?.toLongDefault(): Long {
    return this?.toLong() ?: 0
}
