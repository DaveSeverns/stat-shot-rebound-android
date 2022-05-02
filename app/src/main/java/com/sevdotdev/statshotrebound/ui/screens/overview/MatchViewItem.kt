package com.sevdotdev.statshotrebound.ui.screens.overview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sevdotdev.statshotrebound.domain.model.Match
import com.sevdotdev.statshotrebound.domain.model.TeamInMatch

@Composable
fun MatchViewItem(
    matchState: Match,
    modifier: Modifier = Modifier
        .padding(16.dp)
        .background(MaterialTheme.colors.background)
        .fillMaxWidth()
) {
    Row(
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Text(text = matchState.datePlayed, style = MaterialTheme.typography.subtitle1)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        TeamScoreText(teamData = matchState.awayTeamData)
        Text(text = "VS", style = MaterialTheme.typography.h6)
        TeamScoreText(teamData = matchState.homeTeamData)
    }
}

@Composable
fun TeamScoreText(
    teamData: TeamInMatch
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = teamData.teamName,
            style = MaterialTheme.typography.h5
        )
        Text(text = teamData.score.toString(), style = MaterialTheme.typography.h4)
    }
}
