package com.sevdotdev.statshotrebound.data.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stats(
    @Json(name = "assists")
    val assists: Double? = null,
    @Json(name = "blocks")
    val blocks: Double? = null,
    @Json(name = "conceded_goals")
    val concededGoals: Double? = null,
    @Json(name = "contributed_goals")
    val contributedGoals: Double? = null,
    @Json(name = "faceoffs_lost")
    val faceoffsLost: Double? = null,
    @Json(name = "faceoffs_won")
    val faceoffsWon: Double? = null,
    @Json(name = "game_winning_goals")
    val gameWinningGoals: Double? = null,
    @Json(name = "games_played")
    val gamesPlayed: Double? = null,
    @Json(name = "goals")
    val goals: Double? = null,
    @Json(name = "losses")
    val losses: Double? = null,
    @Json(name = "passes")
    val passes: Double? = null,
    @Json(name = "possession_time_sec")
    val possessionTimeSec: Double? = null,
    @Json(name = "post_hits")
    val postHits: Double? = null,
    @Json(name = "primary_assists")
    val primaryAssists: Double? = null,
    @Json(name = "saves")
    val saves: Double? = null,
    @Json(name = "score")
    val score: Double? = null,
    @Json(name = "shots")
    val shots: Double? = null,
    @Json(name = "shutouts")
    val shutouts: Double? = null,
    @Json(name = "shutouts_against")
    val shutoutsAgainst: Double? = null,
    @Json(name = "takeaways")
    val takeaways: Double? = null,
    @Json(name = "turnovers")
    val turnovers: Double? = null,
    @Json(name = "wins")
    val wins: Double? = null
)