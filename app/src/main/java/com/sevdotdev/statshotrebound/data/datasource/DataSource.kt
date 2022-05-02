package com.sevdotdev.statshotrebound.data.datasource

import kotlinx.coroutines.flow.Flow

interface DataSource<IN, OUT> {
    suspend fun save(input: IN)
    suspend fun get(key: String? = null): OUT
    fun getAll(key: String? = null): Flow<List<OUT>>
}