package com.sevdotdev.statshotrebound

import com.sevdotdev.statshotrebound.domain.DataRepository
import com.sevdotdev.statshotrebound.domain.model.Match
import javax.inject.Inject

class DataManager @Inject constructor(
    private val repository: DataRepository<Match>
) {
    suspend fun updateDataFromServer() =
        repository.retrieveFromServer()

}
