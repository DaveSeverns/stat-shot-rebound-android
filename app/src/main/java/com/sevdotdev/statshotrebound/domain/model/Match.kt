package com.sevdotdev.statshotrebound.domain.model

data class Match(
    val metaData: MatchMetaData = MatchMetaData(),
    val homeTeamData: TeamInMatch = TeamInMatch.Home(),
    val awayTeamData: TeamInMatch = TeamInMatch.Away(),
    val datePlayed: String,
) {
    val winner: TeamInMatch
        get() = homeTeamData.takeIf { home ->
            home.score > awayTeamData.score
        } ?: awayTeamData
}

data class MatchMetaData(
    val arena: String = "Slapville",
    val customMercyRule: String? = null,
    val periodsEnable: Boolean = false
)

sealed class TeamInMatch(val score: Int, val teamName: String) {
    class Home(score: Int = 0, teamName: String = "Home"): TeamInMatch(score = score, teamName = teamName)
    class Away(score: Int = 0, teamName: String = "Away"): TeamInMatch(score, teamName = teamName)
}
