package com.sevdotdev.statshotrebound.data.network.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Match(
    @Json(name = "arena")
    val arena: String? = null,
    @Json(name = "current_period")
    val currentPeriod: String? = null,
    @Json(name = "custom_mercy_rule")
    val customMercyRule: String? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "periods_enabled")
    val periodsEnabled: String? = null,
    @Json(name = "players")
    val players: List<Player>? = null,
    @Json(name = "score")
    val score: Score? = null,
    @Json(name = "type")
    val type: String? = null,
    @Json(name = "winner")
    val winner: String? = null
)