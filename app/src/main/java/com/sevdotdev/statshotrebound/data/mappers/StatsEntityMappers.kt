package com.sevdotdev.statshotrebound.data.mappers

import com.sevdotdev.statshotrebound.data.network.entities.Stats
import tables.StatsEntity
import java.util.*

internal fun Stats.toEntity(matchId: String, playerId: String): StatsEntity =
    StatsEntity(
        id = UUID.randomUUID().toString(),
        player_id = playerId,
        match_id = matchId,
        assists = assists ?: NIL,
        blocks = blocks ?: NIL,
        conceded_goals = concededGoals ?: NIL,
        contributed_goals = contributedGoals ?: NIL,
        goals = goals ?: NIL,
        primary_assists = primaryAssists ?: NIL,
        saves = saves ?: NIL,
        score = score ?: NIL,
        shutouts = shutouts ?: NIL,
        shutouts_against = shutoutsAgainst ?: NIL,
        shots = shots ?: NIL,
        faceoffs_lost = faceoffsLost ?: NIL,
        faceoffs_won = faceoffsWon ?: NIL,
        takeaways = takeaways ?: NIL,
        turnovers = turnovers ?: NIL,
        game_winning_goals = gameWinningGoals ?: NIL,
        games_played = gamesPlayed ?: NIL,
        losses = losses ?: NIL,
        wins =  wins ?: NIL,
        possession_time_sec = possessionTimeSec ?: NIL,
        post_hits = postHits ?: NIL,
        passes = passes ?: NIL
    )

const val NIL = 0.0