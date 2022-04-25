package com.sevdotdev.statshotrebound.ui.screens.overview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevdotdev.statshotrebound.data.entities.Stats
import com.sevdotdev.statshotrebound.domain.model.UserInfo
import com.sevdotdev.statshotrebound.domain.usecases.PlayerStatsUseCase
import com.sevdotdev.statshotrebound.domain.usecases.UserInfoUseCase
import com.sevdotdev.statshotrebound.ui.state.ComposeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

typealias StatsState = ComposeState<Stats>

@HiltViewModel
class OverViewViewModel @Inject constructor(
    private val userInfoUseCase: UserInfoUseCase,
    private val playerStatsUseCase: PlayerStatsUseCase
): ViewModel() {
    fun updatePlayerInfo(playerId: String):Boolean {
        if (playerId.isEmpty()){
            return false
        }
        return true
    }

    private val _userInfoState: MutableStateFlow<UserInfo> = MutableStateFlow(UserInfo.NO_USER_IDENTIFIED_YET)
    val userStateInfo: StateFlow<UserInfo> = _userInfoState.asStateFlow()

    private val _playerStatsOverviewState: MutableStateFlow<StatsState> = MutableStateFlow(ComposeState.Loading())
    val playerStatsOverviewState: StateFlow<StatsState> = _playerStatsOverviewState.asStateFlow()

    init {
        viewModelScope.launch {
            userInfoUseCase().collect {
                _userInfoState.emit(it)
            }
        }
    }

}