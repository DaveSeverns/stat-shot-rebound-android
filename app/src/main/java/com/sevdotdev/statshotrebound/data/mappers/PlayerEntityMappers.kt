package com.sevdotdev.statshotrebound.data.mappers

import com.sevdotdev.statshotrebound.data.network.entities.Player
import tables.PlayerEntity
import tables.PlayerInMatchEntity

internal fun Player.toInMatchEntity(matchId: String): PlayerInMatchEntity =
    PlayerInMatchEntity(
        match_id = matchId,
        player_id = gameUserId ?: DEF_PLAYER_ID,
        player_team = team ?: DEF_USER_NAME
    )

internal fun Player.toEntity(): PlayerEntity =
    PlayerEntity(
        player_id = gameUserId ?: DEF_PLAYER_ID,
        player_name = username ?: DEF_USER_NAME
    )

const val DEF_PLAYER_ID = "-1"
const val DEF_USER_NAME = "Slapper69"