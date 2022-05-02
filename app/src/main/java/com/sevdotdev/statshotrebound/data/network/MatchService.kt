package com.sevdotdev.statshotrebound.data.network

import com.sevdotdev.statshotrebound.data.network.entities.MatchJson
import retrofit2.http.GET

interface MatchService {

    @GET("/all-matches")
    suspend fun getAllMatches(): MatchJson
}