package com.sevdotdev.statshotrebound.data.network.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Player(
    @Json(name = "game_user_id")
    val gameUserId: String? = null,
    @Json(name = "stats")
    val stats: Stats? = null,
    @Json(name = "team")
    val team: String? = null,
    @Json(name = "username")
    val username: String? = null
)