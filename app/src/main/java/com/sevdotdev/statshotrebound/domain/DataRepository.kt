package com.sevdotdev.statshotrebound.domain

import kotlinx.coroutines.flow.Flow

interface DataRepository<D> {
    suspend fun get(getKey: String? = null): D
    suspend fun getAll(getKey: String?= null): Flow<List<D>>
    suspend fun save(dataItem: D)
    suspend fun saveAll(dataItems: List<D>)
}