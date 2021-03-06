package com.sevdotdev.statshotrebound.data.network.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MatchJson(
    @Json(name = "matches")
    val matches: List<Match>? = null
)