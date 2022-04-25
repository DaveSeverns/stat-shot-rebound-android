package com.sevdotdev.statshotrebound.data.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Score(
    @Json(name = "away")
    val away: Int? = null,
    @Json(name = "home")
    val home: Int? = null
)