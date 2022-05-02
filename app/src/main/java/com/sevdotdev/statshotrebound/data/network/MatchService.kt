package com.sevdotdev.statshotrebound.data.network

import com.sevdotdev.statshotrebound.data.network.entities.MatchEntity
import retrofit2.http.GET

interface MatchService {

    @GET("/all-matches")
    suspend fun getAllMatches(): MatchEntity
}