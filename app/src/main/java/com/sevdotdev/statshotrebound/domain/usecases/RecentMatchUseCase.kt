package com.sevdotdev.statshotrebound.domain.usecases

import com.sevdotdev.statshotrebound.domain.DataRepository
import com.sevdotdev.statshotrebound.domain.model.Match
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RecentMatchUseCase @Inject constructor(
    private val matchDataRepository: DataRepository<Match>
) {
    suspend operator fun invoke() : Flow<List<Match>> = matchDataRepository.getAll()
}
