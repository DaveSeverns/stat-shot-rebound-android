package com.sevdotdev.statshotrebound.domain.usecases

import com.sevdotdev.statshotrebound.domain.model.UserInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class UserInfoUseCase @Inject constructor(

) {
    operator fun invoke(): Flow<UserInfo> = flowOf(UserInfo.NO_USER_IDENTIFIED_YET)
}
